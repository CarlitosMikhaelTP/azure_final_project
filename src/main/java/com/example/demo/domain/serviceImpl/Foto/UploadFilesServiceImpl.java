package com.example.demo.domain.serviceImpl.Foto;

import com.example.demo.application.service.FotoService.UploadFilesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFilesServiceImpl implements UploadFilesService {

    @Override
    public String handleFileUpload(MultipartFile file, String routeFoto) throws Exception {
        try {
            // CREAR ID ALEATORIO Y UNICO PARA EL NOMBRE DEL ARCHIVO
            String filename = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            // Accediendo al nombre original
            String fileOriginalName = file.getOriginalFilename();

            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;

            if (fileSize > maxFileSize) {
                throw new Exception("File size must be less than or equal to 5MB");
            }

            if (
                    !fileOriginalName.endsWith(".jpg") &&
                            !fileOriginalName.endsWith(".jpeg") &&
                            !fileOriginalName.endsWith(".png")
            ) {
                return "Only JPG, JPEG, PNG files are allowed! ";
            }
            String filesExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = filename + filesExtension;

            File folder = new File("src/main/resources/pictures");
            if (!folder.exists()){
                folder.mkdirs();
            }
            Path path = Paths.get("src/main/resources/pictures/" + newFileName);
            Files.write(path, bytes);

            // Construir y devolver la ruta completa de la imagen guardada
            String rutaImagenGuardada = path.toString();
            return rutaImagenGuardada;
        } catch (Exception e) {
            throw new Exception("Error while uploading file: " + e.getMessage());
        }
    }
}
