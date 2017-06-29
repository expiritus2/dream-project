package com.dreamproject.controller;


import com.dreamproject.dao.RoleDao;
import com.dreamproject.entity.User;
import com.dreamproject.entity.security.UserRole;
import com.dreamproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Principal principal) {
//        if(principal != null){
//            return "redirect:/";
//        }
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String singupPost(@ModelAttribute("user") User user, Model model) {
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
        userService.createUser(user, userRoles);
        return "signup";
    }


}
