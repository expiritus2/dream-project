package com.dreamproject.service.impl;

import com.dreamproject.dao.TargetObjectDao;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.User;
import com.dreamproject.service.SearchObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchObjectServiceImpl implements SearchObjectService {

    @Autowired
    private TargetObjectDao targetObjectDao;


    @Override
    public List<TargetObject> findObjectsByCoordinates(double latitudeDown, double latitudeUp, double longitudeDown, double longitudeUp, User user) {
        return targetObjectDao.findObjectsByCoordinates(latitudeDown, latitudeUp, longitudeDown, longitudeUp, user);
    }
}
