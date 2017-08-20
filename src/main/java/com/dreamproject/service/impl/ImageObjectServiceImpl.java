package com.dreamproject.service.impl;

import com.dreamproject.dao.ImageObjectDao;
import com.dreamproject.entity.ImageObject;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.service.ImageObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageObjectServiceImpl implements ImageObjectService {


    @Autowired
    private ImageObjectDao imageObjectDao;

    @Override
    public void save(ImageObject imageObject) {
        imageObjectDao.save(imageObject);
    }

    public void delete(Long id){
        ImageObject imageObject = new ImageObject();
        imageObject.setId(id);
        imageObjectDao.delete(id);
    }

    public void deleteAllByTargetObjectId(Long targetObjectId){
        imageObjectDao.deleteAllByTargetObjectId(targetObjectId);
    }
}
