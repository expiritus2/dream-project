package com.dreamproject.service.impl;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.dreamproject.config.WebConfig;
import com.dreamproject.entity.ImageObject;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.service.FileUploadService;
import com.dreamproject.service.ImageObjectService;
import com.dreamproject.service.TargetObjectService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private ImageObjectService imageObjectService;

    @Autowired
    private TargetObjectService targetObjectService;

    private static final Logger LOG = LoggerFactory.getLogger(FileUploadService.class);


    @Override
    public ResponseEntity<?> uploadFiles(MultipartFile[] files, HttpServletRequest request, Long targetObjectId) {

        LOG.debug("Multiple file upload");

        String uploadFileName = Arrays.stream(files).map(file -> file.getOriginalFilename())
                .filter(file -> !StringUtils.isEmpty(file)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadFileName)) {
            return new ResponseEntity<Object>("Please select a file!", HttpStatus.OK);
        }

        try {
            TargetObject targetObject = targetObjectService.findOne(targetObjectId);
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String unicFileName = saveUploadedFile(file);
                imageObjectService.save(new ImageObject(unicFileName, targetObject));
            }
        } catch (IOException e) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Object>("Successfully uploaded - " + uploadFileName, HttpStatus.OK);
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {

        try {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(WebConfig.ACCESS_KEY_ID, WebConfig.SECRET_KEY_ID);

            AmazonS3Client s3Client = new AmazonS3Client(awsCreds);

//            for(Bucket bucket : s3Client.listBuckets()){
//                s3Client.deleteBucket(bucket.getName());
//                System.out.println(bucket.getName());
//            }


//            String newBucketName = "dream-project/imageObject";
//            s3Client.createBucket(newBucketName);

            String originalFileName = file.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String unicFileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);


            InputStream is = file.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest("dream-project/imageObject", unicFileName, is, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult response = s3Client.putObject(putObjectRequest);
            System.out.println("Uploaded object ecryption status is " + response.getSSEAlgorithm());
        } catch (Exception e) {

        }
//        byte[] bytes = file.getBytes();
//        String originalFileName = file.getOriginalFilename();
//        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//        String unicFileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
//        Path path = Paths.get(WebConfig.UPLOADED_FOLDER + "/" + unicFileName);
//        Files.write(path, bytes);
//        return unicFileName;

//        String originalFileName = file.getOriginalFilename();
//        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//        String unicFileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
//
//        AmazonS3Client s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
//
//        String bucketName = "rende-" + UUID.randomUUID();
//        s3Client.createBucket(bucketName);
//        try {
//            InputStream is = file.getInputStream();
//            s3Client.putObject(new PutObjectRequest(bucketName, unicFileName, is, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
//
//            S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, unicFileName));
//        } catch (IOException e){
//
//        }
//
//        return unicFileName;
        return null;
    }

    public static String createAndPopulateSimpleBucket() throws Exception {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(WebConfig.ACCESS_KEY_ID, WebConfig.SECRET_KEY_ID);

        AmazonS3Client s3Client = new AmazonS3Client(awsCreds);

        String newBucketName = "mattua" + System.currentTimeMillis();
        s3Client.createBucket(newBucketName);

        final String fileName = "sometext.txt";

        return newBucketName;

    }
}
