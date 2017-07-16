package com.dreamproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    ResponseEntity<?> uploadFiles(MultipartFile[] files);
}
