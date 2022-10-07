package com.stonks.candidatestracker.services;

import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public interface S3Service {
    URL uploadFile(MultipartFile file);
}
