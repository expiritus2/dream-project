package com.dreamproject.service;

import com.amazonaws.services.s3.AmazonS3Client;

public interface AwsS3Service {

    AmazonS3Client s3Client();

    public String createSimpleBucket(String newBucketName) throws Exception;
}
