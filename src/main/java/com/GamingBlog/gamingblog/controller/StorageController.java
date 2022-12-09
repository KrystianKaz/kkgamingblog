package com.GamingBlog.gamingblog.controller;

import com.GamingBlog.gamingblog.exception.UserImageUploadException;
import com.GamingBlog.gamingblog.message.ResponseMessage;
import com.GamingBlog.gamingblog.model.FileInfo;
import com.GamingBlog.gamingblog.model.User;
import com.GamingBlog.gamingblog.service.StorageService;
import com.GamingBlog.gamingblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class StorageController {

    private final StorageService storageService;
    private final UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/uploadUserImage/{id}")
    public RedirectView uploadUserImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
                                        Principal principal) {

        String message = "";

        User userById = userService.findUserById(id);
        String username = userById.getUsername();

        if (principal.getName().equals(username)) {
            Path root = Paths.get("src", "main/webapp/resources/assets/img/post/" + file.getOriginalFilename());
            if (!Files.exists(root)) {
                storageService.save(file);
                userService.saveUserImage(id, file.getOriginalFilename());
            } else {
                message = "Image with that name already exists!";
                throw new UserImageUploadException(message);
            }
        } else if (!principal.getName().equals(username)) {
            message = "You cannot upload image to other user!";
            throw new UserImageUploadException(message);
        } else {
            message = "You cannot upload image with name " + file.getOriginalFilename();
            throw new UserImageUploadException(message);
        }
        return new RedirectView("/account");
    }


    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(StorageController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).toList();
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
