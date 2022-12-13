package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.UserDisabledException;
import com.GamingBlog.gamingblog.exception.UserNotExistException;
import com.GamingBlog.gamingblog.model.User;
import com.GamingBlog.gamingblog.model.enums.UserRolesEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(value = "/db.migration/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class MyUserDetailsServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Test
    void should_return_correct_user_data_to_build_for_authentication() {
        // given
        User user = new User(4, "johndoe@example.com", "John Doe", "password",
                true, LocalTime.now(), LocalDate.now(), UserRolesEnum.USER, "default.jpg");
        userService.addUser(user.getEmail(), user.getUsername(), user.getPassword());

        // when
        UserDetails userDetails = myUserDetailsService.loadUserByUsername("John Doe");

        // then
        assertFalse(userDetails.getAuthorities().isEmpty());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    void should_throw_UsernameNotFoundException_when_building_authenticaton_for_non_exist_user() {
        // given + when
        String username = "John Wick";
        Throwable exception = assertThrows(UserNotExistException.class,
                () -> myUserDetailsService.loadUserByUsername(username));

        // then
        assertEquals("User with username: " + username + " not exists!", exception.getMessage());
    }

    @Test
    void should_throw_UserDisabledException_when_building_authentication_for_disabled_user() {
        // given + when
        String username = "test";
        Throwable exception = assertThrows(UserDisabledException.class,
                () -> myUserDetailsService.loadUserByUsername(username));

        // then
        assertEquals("User with username: " + username + " is disabled!", exception.getMessage());
    }

    @Test
    void should_return_list_of_Granted_Authorities_for_user() {
        // given
        User user1 = userService.findUserById(1L);
        User user2 = userService.findUserById(2L);

        // when
        List<GrantedAuthority> userAuthority1 = myUserDetailsService.getUserAuthority(user1);
        List<GrantedAuthority> userAuthority2 = myUserDetailsService.getUserAuthority(user2);

        // then
        assertEquals(userAuthority1.size(), 1);
        assertEquals(userAuthority2.size(), 1);
        assertEquals(userAuthority1.get(0).toString(), "ADMIN");
        assertEquals(userAuthority2.get(0).toString(), "USER");
    }

    @Test
    void should_build_user_for_authentication() {
        // given
        User user = userService.findUserById(1L);
        List<GrantedAuthority> userAuthority = myUserDetailsService.getUserAuthority(user);

        // when
        UserDetails userDetails = myUserDetailsService.buildUserForAuthentication(user, userAuthority);

        // then
        assertTrue(userDetails.isCredentialsNonExpired());
        assertEquals(userDetails.getAuthorities().toString(), "[ADMIN]");
    }
}