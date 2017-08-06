package com.dreamproject.service.impl;

import com.dreamproject.service.FileUploadService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger LOG = LoggerFactory.getLogger(FileUploadService.class);

    private static final String UPLOADED_FOLDER = "src/main/resources/files";


    @Override
    public ResponseEntity<?> uploadFiles(MultipartFile[] files, HttpServletRequest request) {

        LOG.debug("Multiple file upload");

        String uploadFileName = Arrays.stream(files).map(file -> file.getOriginalFilename())
                .filter(file -> !StringUtils.isEmpty(file)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadFileName)) {
            return new ResponseEntity<Object>("Please select a file!", HttpStatus.OK);
        }

        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                saveUploadedFile(file);
            }
        } catch (IOException e) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Object>("Successfully uploaded - " + uploadFileName, HttpStatus.OK);
    }

    private void saveUploadedFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String unicFileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        Path path = Paths.get(UPLOADED_FOLDER + "/" + unicFileName);
        Files.write(path, bytes);
    }
}
