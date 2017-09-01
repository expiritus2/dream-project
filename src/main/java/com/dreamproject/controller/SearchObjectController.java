package com.dreamproject.controller;

import com.dreamproject.entity.TargetObject;
import com.dreamproject.utils.ParseUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/target")
@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
public class SearchObjectController {

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<TargetObject> search(@RequestBody String body){
        Map<String, Object> params = ParseUtils.parseJson(body);
        return null;
    }
}
