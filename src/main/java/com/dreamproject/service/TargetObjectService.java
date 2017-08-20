package com.dreamproject.service;

import com.dreamproject.entity.TargetObject;

import java.util.List;

public interface TargetObjectService {

    TargetObject save(TargetObject targetObject);

    List<TargetObject> findAll();

    List<TargetObject> findObjectsByUserId(long id);

    TargetObject findOne(Long targetObjectId);

    void delete(Long id);

}
