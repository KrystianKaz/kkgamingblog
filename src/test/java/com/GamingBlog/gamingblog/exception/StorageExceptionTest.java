package com.GamingBlog.gamingblog.exception;

import com.GamingBlog.gamingblog.service.StorageService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StorageExceptionTest {

    @Autowired
    private StorageService storageService;

    @BeforeEach
    public final void setUp() {
        byte[] bytes = new byte[1024];
        storageService.save(new MockMultipartFile("test.jpg", "test.jpg", "image", bytes));
    }

    @AfterEach
    public final void tearDown() {
        storageService.deleteOne("test.jpg");
    }

    @Test
    void should_throw_StorageException_when_saving_file_that_exists_in_resources() {
        // given
        byte[] bytes = new byte[1024];
        MockMultipartFile file = new MockMultipartFile("test.jpg", "test.jpg",
                "image", bytes);

        // when
        Exception exception = assertThrows(StorageException.class, () -> storageService.save(file));

        // then
        assertEquals(exception.getMessage(), "Failed to store file.");

    }
}