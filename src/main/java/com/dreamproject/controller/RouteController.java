package com.dreamproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouteController {

    @RequestMapping(value = {"/admin", "/admin/**"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/personal-area")
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public String personalArea() {
        return "forward:/index.html";
    }

    @RequestMapping(value = {"/", "/login", "/signup"}, method = RequestMethod.GET)
    public String home() {
        return "forward:/index.html";
    }

}
