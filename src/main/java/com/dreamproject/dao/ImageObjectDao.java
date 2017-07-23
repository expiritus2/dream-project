package com.dreamproject.dao;

import com.dreamproject.entity.ImageObject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImageObjectDao extends CrudRepository<ImageObject, Long>{
    List<ImageObject> findAllByTargetObjectId(long id);
}
