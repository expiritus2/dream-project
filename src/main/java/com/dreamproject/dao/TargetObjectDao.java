package com.dreamproject.dao;

import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.User;
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

    @Modifying
    @Transactional
    @Query(value = "select t from TargetObject t where t.latitude >= ?1 and t.latitude <= ?2 and t.longitude >= ?3 and t.longitude <= ?4 and t.user <> ?5")
    List<TargetObject> findObjectsByCoordinates(double latitudeDown, double latitudeUp, double longitudeDown, double longitudeUp, User user);
}

