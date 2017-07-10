package com.dreamproject.service;

import com.dreamproject.entity.TypeObject;

import java.util.List;

public interface TypeObjectService {

    void save(TypeObject typeObject);

    public List<TypeObject> findAll();
}
