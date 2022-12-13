package com.GamingBlog.gamingblog.exception;

import com.GamingBlog.gamingblog.controller.UserController;
import com.GamingBlog.gamingblog.model.User;
import com.GamingBlog.gamingblog.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Sql(value = "/db.migration/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AccessDeniedExceptionTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Mock
    private Model model;

    @Test
    void should_throw_AccessDeniedException_when_user_trying_to_edit_other_user_data() {
        // given
        User firstUserById = userService.findUserById(1L);
        User secondUserById = userService.findUserById(2L);
        final var principal = mock(Principal.class);
        when(principal.getName()).thenReturn(firstUserById.getUsername());

        Throwable exception = assertThrows(AccessDeniedException.class,
                () -> userController.userImageManager(secondUserById.getId(), model, principal));

        // then
        assertEquals(exception.getMessage(), "You don't have access to user page with id: " + secondUserById.getId());
    }

}