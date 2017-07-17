package com.dreamproject.service.impl;

import com.dreamproject.dao.ImageObjectDao;
import com.dreamproject.entity.ImageObject;
import com.dreamproject.service.ImageObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageObjectServiceImpl implements ImageObjectService {


    @Autowired
    private ImageObjectDao imageObjectDao;

    @Override
    public void save(ImageObject imageObject) {
        imageObjectDao.save(imageObject);
    }
}
