package com.dreamproject.controller;

import com.dreamproject.dao.UserDao;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.User;
import com.dreamproject.service.SearchObjectService;
import com.dreamproject.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/target")
@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
public class SearchObjectController {

    @Autowired
    private SearchObjectService searchObjectService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<TargetObject> search(@RequestBody String body, Principal principal) {
        Map<String, Object> params = ParseUtils.parseJson(body);
        double latitude = Double.parseDouble(params.get("_lat").toString());
        double longitude = Double.parseDouble(params.get("_lng").toString());

        double latitudeBorder = 0.085;
        double longitudeBorder = 0.13;


        double latitudeDown = latitude - latitudeBorder;
        double latitudeUp = latitude + latitudeBorder;

        double longitudeDown = longitude - longitudeBorder;
        double longitudeUp = longitude + longitudeBorder;

        System.out.println(latitudeDown);
        System.out.println(latitudeUp);

        User user = userDao.findByUsername(principal.getName());

        return searchObjectService.findObjectsByCoordinates(latitudeDown, latitudeUp, longitudeDown, longitudeUp, user);

    }
}
