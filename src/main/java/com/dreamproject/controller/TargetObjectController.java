package com.dreamproject.controller;

import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.TypeObject;
import com.dreamproject.entity.User;
import com.dreamproject.service.FileUploadService;
import com.dreamproject.service.TargetObjectService;
import com.dreamproject.service.TypeObjectService;
import com.dreamproject.service.UserService;
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
import java.security.Principal;
import java.util.List;
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
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        return fileUploadService.uploadFiles(files, request);
    }

    @RequestMapping(value = "/putObject", method = RequestMethod.POST, produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> putObject(@RequestBody String body){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> param = jsonParser.parseMap(body);
        String nameObject = param.get("name").toString();
        TypeObject typeObject = getTypeObjectWithLocale(nameObject);
        if(typeObject == null){
            typeObject = typeObjectService.save(new TypeObject(nameObject));
        }
        return new ResponseEntity<Object>(body, HttpStatus.OK);
    }

    private TypeObject getTypeObjectWithLocale(String name){
        return typeObjectService.findByNameEnOrNameRu(name);
    }
}
