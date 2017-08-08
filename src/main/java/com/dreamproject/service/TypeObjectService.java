package com.dreamproject.service;

import com.dreamproject.entity.TypeObject;

import java.util.List;

public interface TypeObjectService {

    TypeObject save(TypeObject typeObject);

    List<TypeObject> findAll();

    TypeObject findByNameEnOrNameRu(String name);
}
