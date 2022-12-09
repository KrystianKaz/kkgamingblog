package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.*;
import com.GamingBlog.gamingblog.model.User;
import com.GamingBlog.gamingblog.model.enums.UserRolesEnum;
import com.GamingBlog.gamingblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void activateOrDeactivateUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    public void addUser(String email, String username, String password) {
        if (!checkIfUserWithGivenNicknameIsInDatabase(username)) ;
        {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = User.builder()
                    .email(emailMatcher(emailCheckInDatabase(email)))
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .active(true)
                    .accountCreationTime(LocalTime.now())
                    .accountCreationDate(LocalDate.now())
                    .userRoles(UserRolesEnum.USER).file("default.jpg")
                    .build();
            userRepository.save(user);
        }
    }

    public String emailMatcher(String email) {
        if (!email.matches("^[A-Za-z\\d._%+-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Wrong email format");
        }
        else return email;
    }

    public String emailCheckInDatabase(String email) throws CreatedUserExistException {
        List<User> all = userRepository.findAll();
        for (User user : all) {
            if (user.getEmail().equals(email)) {
                throw new CreatedUserExistException("User with given email already exists");
            }
        }
        return email;
    }

    public User findUserByUsername(String username) {
        String message = "User with username: " + username + " not exists!";
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotExistException(message));

        if (!user.isActive()) {
            throw new UserDisabledException("User with username: " + username + " is disabled!");
        }
        return user;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
    }

    public boolean checkIfUserWithGivenNicknameIsInDatabase(String username) {
        Optional<User> byNickname = userRepository.findByUsername(username);
        if (byNickname.isPresent()) {
            throw new CreatedUserExistException("User with this username: " + username + " already exists!");
        }
        return false;
    }

    //used in game.jsp & news.jsp
    public String findUserFileByUsername(String username) {
        User byUsername = userRepository.findByUsername(username).orElseThrow(() -> new RequestNotFoundException(username));
        return byUsername.getFile();
    }

    public void setYourImageAsDefault(Long id, String username) {
        User user = userRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
        if (!user.getUsername().equals(username)) {
            throw new UserImageUploadException("You cannot upload image to other user!");
        }
        user.setFile("default.jpg");
        userRepository.save(user);
    }

    //Only for users with role ADMIN!
    public void setUserImageAsDefault(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
        user.setFile("default.jpg");
        userRepository.save(user);
    }

    public void saveUserImage(Long id, String image) {
        User user = userRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
        user.setFile(image);
        userRepository.save(user);
    }

    public void editUser(User user) {
        user.setAccountCreationDate(LocalDate.now());
        user.setAccountCreationTime(LocalTime.now());
        userRepository.save(user);
    }
}
