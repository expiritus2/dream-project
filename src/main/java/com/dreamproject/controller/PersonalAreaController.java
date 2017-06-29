package com.dreamproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/personal-area")
@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
public class PersonalAreaController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String personalArea(){
        return "personal-area/index";
    }
}
