package com.dreamproject.service;

import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.User;

import java.util.List;

public interface SearchObjectService {

    List<TargetObject> findObjectsByCoordinates(double latitudeDown, double latitudeUp, double longitudeDown, double longitudeUp, User user);
}
