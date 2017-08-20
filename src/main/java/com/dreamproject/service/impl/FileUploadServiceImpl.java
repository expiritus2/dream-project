package com.dreamproject.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
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

    @Autowired
    private AmazonS3Client s3Client;

    private static final Logger LOG = LoggerFactory.getLogger(FileUploadService.class);


    @Override
    public ResponseEntity<?> uploadFiles(MultipartFile[] files, HttpServletRequest request, Long targetObjectId, String bucketName) {
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
                String unicFileName = saveUploadedFile(file, bucketName);
                imageObjectService.save(new ImageObject(unicFileName, targetObject));
            }
        } catch (IOException e) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Object>("Successfully uploaded - " + uploadFileName, HttpStatus.OK);
    }

    private String saveUploadedFile(MultipartFile file, String bucketName) throws IOException {
        String unicFileName;
        try {
            String originalFileName = file.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            unicFileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);

            InputStream is = file.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, unicFileName, is, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult response = s3Client.putObject(putObjectRequest);
            System.out.println("Uploaded object ecryption status is " + response.getSSEAlgorithm());
        } catch (Exception e) {
            throw new RuntimeException("Can't upload file", e);
        }
        return unicFileName;
    }
}
