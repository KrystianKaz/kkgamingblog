package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.*;
import com.GamingBlog.gamingblog.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(value = "/db.migration/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @Test
    void should_create_new_user_when_given_correct_data() {
        // given + when
        String email = "johndoe@example.com";
        String username = "JohnDoe";
        String password = "john";
        userService.addUser(email, username, password);

        List<User> allUsers = userService.findAllUsers();

        // then
        assertEquals(allUsers.size(), 4);
        assertEquals(allUsers.get(3).getUsername(), username);
    }

    @Test
    void should_throw_CreatedUserExistException_when_registering_user_that_username_is_in_db() {
        // given + when
        String username = "user";
        Throwable exception = assertThrows(CreatedUserExistException.class,
                () -> userService.addUser("newuser@example.com", username, "password"));

        // then
        assertEquals("User with this username: " + username + " already exists!", exception.getMessage());
    }


    @DisplayName("'Wrong' email given in parameters should throw IllegalArgumentException")
    @Test
    void should_throw_IllegalArgumentException_when_given_email_not_matches_to_regex() {
        // given + when
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> userService.addUser("wrongMail",
                "tester", "tester"));

        // then
        assertEquals("Wrong email format", exception.getMessage());
    }

    @Test
    void should_throw_CreatedUserExistException_when_user_with_given_username_already_exists() {
        // given + when
        String username = "admin";
        Throwable exception = assertThrows(CreatedUserExistException.class, () -> userService
                .checkIfUserWithGivenNicknameIsInDatabase(username));

        //then
        assertEquals("User with this username: " + username + " already exists!", exception.getMessage());
    }

    @Test
    void should_throw_CreatedUserExistException_when_user_with_given_email_already_exists() {
        // given + when
        String email = "admin@admin.com";
        Throwable exception = assertThrows(CreatedUserExistException.class, () -> userService.emailCheckInDatabase(email));

        //then
        assertEquals("User with given email already exists", exception.getMessage());
    }

    @Test
    void should_deactivate_user_account() {
        // given + when
        userService.activateOrDeactivateUserById(1L);
        User user = userService.findUserById(1L);

        // then
        assertFalse(user.isActive());
    }

    @Test
    void should_activate_user_account() {
        // given + when
        userService.activateOrDeactivateUserById(3L);
        User user = userService.findUserById(3L);

        //then
        assertTrue(user.isActive());
    }

    @Test
    void should_throw_RequestNotFoundException_trying_to_activate_user_with_id_not_found() {
        // given + when
        long id = 4;
        Throwable exception = assertThrows(RequestNotFoundException.class,
                () -> userService.activateOrDeactivateUserById(id));

        // then
        assertEquals("Could not find request with id: " + id, exception.getMessage());
    }

    @Test
    void user_not_found_with_given_id_should_throw_RequestNotFoundException() {
        // given + when
        long id = 4;
        Throwable exception = assertThrows(RequestNotFoundException.class, () -> userService.findUserById(id));

        // then
        assertEquals("Could not find request with id: " + id, exception.getMessage());
    }

    @Test
    void should_return_User_by_giving_username() {
        //given + when
        String username = "admin";
        User userByUsername = userService.findUserByUsername(username);

        // then
        assertEquals(userByUsername.getUsername(), username);
    }

    @Test
    void should_throw_RequestNotFoundException_while_searching_image_for_user_that_his_username_not_in_db() {
        // given + when
        String username = "John Doe";
        Throwable exception = assertThrows(RequestNotFoundException.class, () -> userService.findUserFileByUsername(username));

        // then
        assertEquals("Could not find request named: " + username, exception.getMessage());
    }

    @Test
    void should_throw_UserNotExistException_when_user_with_given_username_not_found() {
        // given + when
        String username = "JohnDoe";
        Throwable exception = assertThrows(UserNotExistException.class, () -> userService.findUserByUsername(username));

        // then
        assertEquals("User with username: " + username + " not exists!", exception.getMessage());
    }

    @Test
    void should_throw_UserDisabledException_when_given_user_is_not_active() {
        //given + when
        String username = "test";
        Throwable exception = assertThrows(UserDisabledException.class, () -> userService.findUserByUsername(username));

        // then
        assertEquals("User with username: " + username + " is disabled!", exception.getMessage());
    }

    @Test
    void should_return_filename_of_given_user_username() {
        // given + when
        String username = "admin";
        String userFileByUsername = userService.findUserFileByUsername(username);

        //then
        assertEquals(userFileByUsername, "admin.jpg");
    }

    @Test
    void admin_should_change_other_user_image_to_default() {
        // given + when
        long id = 1;
        userService.setUserImageAsDefault(id);
        User userById = userService.findUserById(id);

        //then
        assertEquals(userById.getFile(), "default.jpg");
    }

    @Test
    void should_throw_RequestNotFoundException_when_trying_set_default_image_to_user_that_is_not_in_db() {
        // given + when
        long id = 4;
        Throwable exception = assertThrows(RequestNotFoundException.class,
                () -> userService.setUserImageAsDefault(id));

        // then
        assertEquals("Could not find request with id: " + id, exception.getMessage());
    }

    @Test
    void should_save_new_user_image() {
        // given
        long id = 1;
        String file = "avatar.jpg";

        // when
        userService.saveUserImage(id, file);
        User userById = userService.findUserById(id);

        //then
        assertEquals(userById.getFile(), file);
    }

    @Test
    void should_throw_RequestNotFoundException_when_saving_user_image_to_user_that_is_not_in_db() {
        // given
        long id = 4;
        String username = "John Doe";

        // when
        Throwable exception= assertThrows(RequestNotFoundException.class,
                () -> userService.saveUserImage(id, username));

        // then
        assertEquals("Could not find request with id: " + id, exception.getMessage());
    }

    @Test
    void admin_should_edit_user_data() {
        // given
        long id = 3;
        User userById = userService.findUserById(id);

        // when
        userById.setUsername("UsernameEdited");
        userById.setPassword("newpassword");
        userById.setEmail("newemail@example.com");
        userService.editUser(userById);

        // then
        assertAll("Group of assertions for edited User",
                () -> assertEquals(userById.getUsername(), "UsernameEdited"),
                () -> assertEquals(userById.getPassword(), "newpassword"),
                () -> assertEquals(userById.getEmail(), "newemail@example.com")
        );
    }

    @Test
    @WithMockUser(username = "user2", password = "user2", roles = "USER")
    void user_should_not_to_change_of_other_users_image_to_default() {
        // given
        long id = 1;
        String mockUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // when
        Throwable exception = assertThrows(UserImageUploadException.class,
                () -> userService.setYourImageAsDefault(id, mockUsername));

        // then
        assertEquals("You cannot upload image to other user!", exception.getMessage());
    }

    @Test
    void should_throw_RequestNotFoundException_when_setting_image_to_user_that_not_exists_in_db() {
        // given
        long id = 4;
        String username = "John Doe";

        // when
        Throwable exception = assertThrows(RequestNotFoundException.class,
                () -> userService.setYourImageAsDefault(id, username));

        // then
        assertEquals("Could not find request with id: " + id, exception.getMessage());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void user_should_be_able_to_change_own_image() {
        // given + when
        long id = 2;
        String mockUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.setYourImageAsDefault(id, mockUsername);
        User userById = userService.findUserById(id);

        //then
        assertNotEquals(userById.getFile(), "user.jpg");
        assertEquals(userById.getFile(), "default.jpg");
    }
}