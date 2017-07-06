package com.dreamproject.service;

import com.dreamproject.entity.TargetObject;

import java.util.List;

public interface TargetObjectService {

    void save(TargetObject targetObject);

    List<TargetObject> findAll();
}
