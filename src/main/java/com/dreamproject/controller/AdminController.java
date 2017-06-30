package com.dreamproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String admin(){
        return "forward:/index.html";
    }
}
