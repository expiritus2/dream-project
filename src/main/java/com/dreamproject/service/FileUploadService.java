package com.dreamproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

    ResponseEntity<?> uploadFiles(MultipartFile[] files, HttpServletRequest request, Long targetObjectId, String bucketName);
}
