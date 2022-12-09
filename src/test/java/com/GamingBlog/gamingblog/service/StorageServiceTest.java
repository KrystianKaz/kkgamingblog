package com.GamingBlog.gamingblog.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class StorageServiceTest {

    @Autowired
    StorageService storageService;

    @AfterEach
    void tearDown() {
        storageService.deleteOne("test.jpg");
    }

    @Test
    void should_load_file_from_resources() {
        // given
        byte[] bytes = new byte[1024];
        MockMultipartFile file = new MockMultipartFile("test.jpg", "test.jpg", "image", bytes);
        storageService.save(file);

        // when
        Resource load = storageService.load(file.getOriginalFilename());

        // then
        assertTrue(load.exists());
        assertEquals(load.getFilename(), file.getOriginalFilename());
    }
}