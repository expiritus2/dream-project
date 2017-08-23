package com.dreamproject.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.dreamproject.config.WebConfig;
import com.dreamproject.dao.ImageObjectDao;
import com.dreamproject.entity.ImageObject;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.service.AwsS3Service;
import com.dreamproject.service.ImageObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageObjectServiceImpl implements ImageObjectService {


    @Autowired
    private ImageObjectDao imageObjectDao;

    @Autowired
    private AwsS3Service awsS3Service;

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
        List<ImageObject> allByTargetObjectId = imageObjectDao.findAllByTargetObjectId(targetObjectId);
        imageObjectDao.deleteAllByTargetObjectId(targetObjectId);
        for(ImageObject imageObject : allByTargetObjectId){
            awsS3Service.deleteObject(imageObject.getName());
        }
    }

    public List<ImageObject> findAll(){
        return (List<ImageObject>) imageObjectDao.findAll();
    }
}
