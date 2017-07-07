package com.dreamproject.dao;

import com.dreamproject.entity.TargetObject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TargetObjectDao extends CrudRepository<TargetObject, Long> {

    List<TargetObject> findAllByUserUserId(long id);
}
