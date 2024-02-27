package com.smarthome.smarthome.helpers;

import com.smarthome.smarthome.exceptions.DataNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileHelper {
    static public String storeImage(MultipartFile file, String uploadFolder) throws IOException {
        if (!isImageFile(file) || file.getOriginalFilename() == null)
            throw new IOException("Invalid file");
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path uploadDir = Paths.get(uploadFolder);
        if(!Files.exists(uploadDir))
            Files.createDirectories(uploadDir);
        Path destination = Paths.get(uploadDir.toString(), fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    static private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image");
    }

    static public Resource loadFile(Path root , String fileName) throws Exception {
        Path file = root.resolve(fileName);
        Resource resource = new UrlResource(file.toUri());
        if(resource.exists() || resource.isReadable())
            return resource;
        else throw new DataNotFoundException("Cannot find this image");
    }
}
