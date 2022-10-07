package com.stonks.candidatestracker.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
@Log4j2
public class S3ServicePdfUploadImpl implements S3Service {

    private final AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public S3ServicePdfUploadImpl(AmazonS3 amazonS3) {
        this.s3client = amazonS3;
    }

    @Override
    public URL uploadFile(MultipartFile file) {
        try {

            String originalName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String fileExtension = FilenameUtils.getExtension(originalName);
            String contentType = file.getContentType();

            if (fileExtension.equals("pdf"))
                return this.uploadFile(inputStream, originalName, contentType);
            throw new IllegalArgumentException("You are trying to upload something that is not a pdf");

        } catch (IOException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    private URL uploadFile(InputStream inputStream, String fileName, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        log.info("Upload start");
        s3client.putObject(bucketName, fileName, inputStream, objectMetadata);
        log.info("Upload finish");
        return s3client.getUrl(bucketName, fileName);
    }
}
