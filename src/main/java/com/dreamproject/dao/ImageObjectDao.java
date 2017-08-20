package com.dreamproject.dao;

import com.dreamproject.entity.ImageObject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ImageObjectDao extends CrudRepository<ImageObject, Long>{
    List<ImageObject> findAllByTargetObjectId(long id);

    @Modifying
    @Transactional
    @Query(value = "delete from ImageObject i where i.targetObject.id = ?1")
    void deleteAllByTargetObjectId(Long id);

}
