package com.dreamproject.service;


import com.dreamproject.entity.ImageObject;

public interface ImageObjectService {

    void save(ImageObject imageObject);

    void delete(Long id);

    void deleteAllByTargetObjectId(Long targetObjectId);

}
