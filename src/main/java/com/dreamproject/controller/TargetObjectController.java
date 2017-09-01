package com.dreamproject.controller;

import com.dreamproject.config.WebConfig;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.TypeObject;
import com.dreamproject.entity.User;
import com.dreamproject.service.*;
import com.dreamproject.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/target")
@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
public class TargetObjectController {

    @Autowired
    private TargetObjectService targetObjectService;

    @Autowired
    private TypeObjectService typeObjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private AwsS3Service awsS3Service;


    @RequestMapping(value = "/findOwn", method = RequestMethod.GET)
    public List<TargetObject> getObjects(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return targetObjectService.findObjectsByUserId(user.getUserId());
    }

    @RequestMapping(value = "/findAllExistsNames", method = RequestMethod.GET)
    public List<TypeObject> getAllExistsNamesObjects() {
        return typeObjectService.findAll();
    }


    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile[] files, @RequestParam("targetObjectId") Long targetObjectId, HttpServletRequest request) throws IOException {
        return fileUploadService.uploadFiles(files, request, targetObjectId, WebConfig.BUCKET_NAME);
    }

    @RequestMapping(value = "/putObject", method = RequestMethod.POST)
    public ResponseEntity<Object> putObject(@RequestBody String body, Principal principal) throws ParseException {
        Map<String, Object> params = ParseUtils.parseJson(body);
        String nameObject = params.get("name").toString();
        double latitude = Double.parseDouble(params.get("latitude").toString());
        double longitude = Double.parseDouble(params.get("longitude").toString());
        String comment = params.get("comment").toString();
        String date = params.get("date").toString();
        boolean draggable = Boolean.parseBoolean(params.get("draggable").toString());
        boolean positionIsChanged = Boolean.parseBoolean(params.get("positionIsChanged").toString());
        String statusObject = params.get("statusObject").toString();
        TypeObject typeObject = getTypeObject(nameObject);
        if(typeObject == null){
            typeObject = typeObjectService.save(new TypeObject(nameObject));
        }

        User user = userService.findByUsername(principal.getName());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm", Locale.ENGLISH);
        calendar.setTime(simpleDateFormat.parse(date));
        TargetObject newTargetObject = new TargetObject(typeObject, latitude, longitude, comment, user, calendar);
        newTargetObject.setDraggable(draggable);
        newTargetObject.setPositionIsChanged(positionIsChanged);
        newTargetObject.setStatusObject(statusObject);
        TargetObject targetObject = targetObjectService.save(newTargetObject);
        if(targetObject != null){
            return new ResponseEntity<Object>(targetObject.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<Object>("Can't save target object", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Object> deleteObject(@RequestBody String body){
        Map<String, Object> params = ParseUtils.parseJson(body);
        Long id = Long.parseLong(params.get("id").toString());
        targetObjectService.delete(id);
        return new ResponseEntity<Object>(body, HttpStatus.OK);
    }


    private TypeObject getTypeObject(String name){
        return typeObjectService.findByName(name);
    }
}
