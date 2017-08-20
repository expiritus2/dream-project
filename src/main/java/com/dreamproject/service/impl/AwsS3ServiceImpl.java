package com.dreamproject.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.dreamproject.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("file:///${user.home}/.expiritus/application-common.properties")
public class AwsS3ServiceImpl implements AwsS3Service {


    @Value("${aws.s3.profile}")
    private String awsProfileName;

    @Bean
    public AmazonS3Client s3Client() {
        AWSCredentials awsCreds = new ProfileCredentialsProvider(awsProfileName).getCredentials();
        return new AmazonS3Client(awsCreds);
    }

    public String createSimpleBucket(String newBucketName) throws Exception {
        AWSCredentials awsCreds = new ProfileCredentialsProvider(awsProfileName).getCredentials();
        AmazonS3Client s3Client = new AmazonS3Client(awsCreds);
        s3Client.createBucket(newBucketName);
        return newBucketName;
    }

}
