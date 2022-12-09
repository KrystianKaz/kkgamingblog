package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.FileUploadExceptionAdvice;
import com.GamingBlog.gamingblog.exception.StorageException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Path root = Paths.get("src", "main/webapp/resources/assets/img/post");

    public void init() {
        if (!Files.exists(root)) {
            try {
                Files.createDirectory(root);
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize folder for upload");
            }
        }
    }

    public void save(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            try {
                Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            } catch (SizeLimitExceededException e) {
                throw new FileUploadException();
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.");
        }
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteOne(String fileName) {
        Path filePath = Paths.get("src", "main/webapp/resources/assets/img/post/" + fileName);
        FileSystemUtils.deleteRecursively(filePath.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    public List<String> listFilesNames(Path dir) {
        return Stream.of(Objects.requireNonNull(new File(String.valueOf(dir)).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }
}

