package com.dreamproject.controller;

import com.dreamproject.entity.TargetObject;
import com.dreamproject.service.TargetObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/target")
public class TargetObjectController {

    @Autowired
    private TargetObjectService targetObjectService;

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<TargetObject> getObjects(){
        return targetObjectService.findAll();
    }
}
