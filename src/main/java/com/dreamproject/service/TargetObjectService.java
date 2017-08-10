package com.dreamproject.service;

import com.dreamproject.entity.TargetObject;

import java.util.List;

public interface TargetObjectService {

    TargetObject save(TargetObject targetObject);

    List<TargetObject> findAll();

    List<TargetObject> findObjectByUserId(long id);

    TargetObject findOne(Long targetObjectId);

}
