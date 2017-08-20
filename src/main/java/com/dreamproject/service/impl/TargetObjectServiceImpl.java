package com.dreamproject.service.impl;

import com.dreamproject.dao.TargetObjectDao;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.service.ImageObjectService;
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
    private ImageObjectService imageObjectService;

    public TargetObject save(TargetObject targetObject) {
        return targetObjectDao.save(targetObject);
    }

    public List<TargetObject> findAll() {
        return (List<TargetObject>) targetObjectDao.findAll();
    }

    public List<TargetObject> findObjectsByUserId(long id) {
        return targetObjectDao.findAllByUserUserId(id);
    }

    @Override
    public TargetObject findOne(Long targetObjectId) {
        return targetObjectDao.findOne(targetObjectId);
    }

    public void delete(Long id) {
        imageObjectService.deleteAllByTargetObjectId(id);
        targetObjectDao.deleteById(id);
    }
}
