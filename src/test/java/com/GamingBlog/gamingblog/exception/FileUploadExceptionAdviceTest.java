package com.GamingBlog.gamingblog.exception;

import com.GamingBlog.gamingblog.service.StorageService;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileUploadExceptionAdviceTest {

    @Autowired
    private StorageService storageService;

    @AfterEach
    public final void tearDown() {
        storageService.deleteOne("test.jpg");
    }

//todo - delete file method, max size upload exception

//    @Test
//    void should_throw_MaxUploadSizeExceededException_while_file_size_exceed_limits() {
//        // given
//        byte[] bytes = new byte[1024*1024];
//        MultipartFile file = new MockMultipartFile("test.jpg", "test.jpg", "image", bytes);
//        // when
//        Exception exception = assertThrows(SizeLimitExceededException.class, () -> storageService.save(file));
//
//        //then
//        assertEquals(exception.getMessage(), "File too large!");
//    }

}