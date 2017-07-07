package com.dreamproject.controller;

import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.User;
import com.dreamproject.service.TargetObjectService;
import com.dreamproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/target")
public class TargetObjectController {

    @Autowired
    private TargetObjectService targetObjectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findOwn", method = RequestMethod.GET)
    public List<TargetObject> getObjects(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return targetObjectService.findObjectByUserId(user.getUserId());
    }
}
