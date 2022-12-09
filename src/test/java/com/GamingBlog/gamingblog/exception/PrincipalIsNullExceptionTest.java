package com.GamingBlog.gamingblog.exception;

import com.GamingBlog.gamingblog.controller.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(value = "/db.migration/V1_init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/V2_insert-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/V3_clean-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PrincipalIsNullExceptionTest {

    @Autowired
    private UserController userController;

    @Mock
    Principal principal;

    @Mock
    Model model;

    @Test
    void should_throw_PrincipalIsNullException_when_trying_to_get_userlist() {
        // given + when
        Throwable exception = assertThrows(PrincipalIsNullException.class, () -> userController.getListOfUsers(model, principal));

        // then
        assertEquals(exception.getMessage(), "Please log in and reload the page");
    }

}