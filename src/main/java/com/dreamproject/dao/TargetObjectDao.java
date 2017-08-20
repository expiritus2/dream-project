package com.dreamproject.dao;

import com.dreamproject.entity.TargetObject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TargetObjectDao extends CrudRepository<TargetObject, Long> {

    List<TargetObject> findAllByUserUserId(long id);

    @Modifying
    @Transactional
    @Query(value = "delete from TargetObject t where t.id = ?1")
    void deleteById(Long id);
}
