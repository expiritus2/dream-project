package com.dreamproject.controller;

import com.dreamproject.dao.RoleDao;
import com.dreamproject.entity.User;
import com.dreamproject.entity.security.UserRole;
import com.dreamproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.PUT)
    public List<User> signupPut(@RequestBody Map<String, Object> parameters, HttpServletResponse response) {
        User userData = new User();
        userData.setFirstName(parameters.get("firstName").toString());
        userData.setLastName(parameters.get("lastName").toString());
        userData.setUsername(parameters.get("email").toString());
        userData.setPassword(parameters.get("password").toString());

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(userData, roleDao.findByName("ROLE_USER")));
        User user = userService.createUser(userData, userRoles);
        List<User> userObject = new ArrayList<>();
        if(user == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return userObject;
        }
        userObject.add(user);
        response.setStatus(HttpServletResponse.SC_OK);
        return userObject;

    }
}
