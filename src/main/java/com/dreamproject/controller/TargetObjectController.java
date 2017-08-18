package com.dreamproject.controller;

import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.TypeObject;
import com.dreamproject.entity.User;
import com.dreamproject.service.*;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin2.message.Message;

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


    @RequestMapping(value = "/findOwn", method = RequestMethod.GET)
    public List<TargetObject> getObjects(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return targetObjectService.findObjectByUserId(user.getUserId());
    }

    @RequestMapping(value = "/findAllExistsNames", method = RequestMethod.GET)
    public List<TypeObject> getAllExistsNamesObjects() {
        return typeObjectService.findAll();
    }


    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile[] files, @RequestParam("targetObjectId") Long targetObjectId, HttpServletRequest request) throws IOException {
        return fileUploadService.uploadFiles(files, request, targetObjectId);
    }

    @RequestMapping(value = "/putObject", method = RequestMethod.POST, produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> putObject(@RequestBody String body, Principal principal) throws ParseException {
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> param = jsonParser.parseMap(body);
        System.out.println(param);
        String nameObject = param.get("name").toString();
        double latitude = Double.parseDouble(param.get("latitude").toString());
        double longitude = Double.parseDouble(param.get("longitude").toString());
        String comment = param.get("comment").toString();
        String date = param.get("date").toString();
        boolean draggable = Boolean.parseBoolean(param.get("draggable").toString());
        boolean positionIsChanged = Boolean.parseBoolean(param.get("positionIsChanged").toString());
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
        TargetObject targetObject = targetObjectService.save(newTargetObject);
        return new ResponseEntity<Object>(targetObject.getId(), HttpStatus.OK);
    }

    private TypeObject getTypeObject(String name){
        return typeObjectService.findByName(name);
    }
}
