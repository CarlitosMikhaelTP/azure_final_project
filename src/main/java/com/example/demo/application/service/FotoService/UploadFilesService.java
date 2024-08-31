package com.example.demo.application.service.FotoService;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFilesService {

    String handleFileUpload (MultipartFile file, String routeFoto) throws Exception;
}
