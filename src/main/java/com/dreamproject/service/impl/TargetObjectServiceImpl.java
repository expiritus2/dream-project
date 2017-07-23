package com.dreamproject.service.impl;

import com.dreamproject.dao.ImageObjectDao;
import com.dreamproject.dao.TargetObjectDao;
import com.dreamproject.entity.ImageObject;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.service.TargetObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TargetObjectServiceImpl implements TargetObjectService {

    private static final Logger LOG = LoggerFactory.getLogger(TargetObjectService.class);

    @Autowired
    private TargetObjectDao targetObjectDao;

    @Autowired
    private ImageObjectDao imageObjectDao;

    public void save(TargetObject targetObject) {
        targetObjectDao.save(targetObject);
    }

    public List<TargetObject> findAll() {
        return (List<TargetObject>)targetObjectDao.findAll();
    }

    public List<TargetObject> findObjectByUserId(long id){
        List<TargetObject> allObjectsByUserId = targetObjectDao.findAllByUserUserId(id);
        return allObjectsByUserId;
    }
}
