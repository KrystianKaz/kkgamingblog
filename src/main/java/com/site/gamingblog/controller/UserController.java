package com.site.gamingblog.controller;


import com.site.gamingblog.exception.AccessDeniedException;
import com.site.gamingblog.exception.PrincipalIsNullException;
import com.site.gamingblog.model.User;
import com.site.gamingblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public RedirectView createNewUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        userService.addUser(email, username, password);
        return new RedirectView("/login");
    }

    @GetMapping("/account")
    public String getUserPage(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/myAccount";
    }

    @GetMapping("/upload")
    public String upload() {
        return "uploadForm";
    }

    @GetMapping("/userList")
    public String getListOfUsers(Model model, Principal principal) throws PrincipalIsNullException{
        List<User> allUsers = userService.findAllUsers();
        model.addAttribute("list", allUsers);

        if(principal.getName()!=null) {
            User findUserByUsername = userService.findUserByUsername(principal.getName());
            model.addAttribute("user", findUserByUsername);
        } else throw new PrincipalIsNullException("Please log in and reload the page");

        return "user/userList";
    }

    @PostMapping("/userStatus/{id}")
    public RedirectView activateOrDeactivateUser(@PathVariable("id") Long id) {
        userService.activateOrDeactivateUserById(id);
        return new RedirectView("/userList");
    }

    @GetMapping("/account/{id}")
    public String userImageManager(@PathVariable("id") Long id, Model model, Principal principal) throws AccessDeniedException {
        if (principal != null && principal.getName().equals(userService.findUserById(id).getUsername())) {
            String name = principal.getName();
            model.addAttribute("user", name);
            User userByUsername = userService.findUserById(id);
            model.addAttribute("editUser", userByUsername);
            return "/user/editUser";
        }
        throw new AccessDeniedException("You don't have access to user page with id: " + id);
    }

    @GetMapping("userList/user/{id}")
    public String getUserPage(@PathVariable("id") Long id, Model model, Principal principal) {
        User userById = userService.findUserById(id);
        model.addAttribute("givenUser", userById);

        User findUserByUsername = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", findUserByUsername);
        return "/user/userPage";
    }

    @PostMapping("/userImage/{id}")
    public RedirectView setUserImageAsDefault(@PathVariable("id") Long id, String username) {
        userService.setYourImageAsDefault(id, username);
        return new RedirectView("/account");
    }

    @PostMapping("/defaultImage/{id}")
    public RedirectView setCurrentUserImageAsDefault(@PathVariable("id") Long id) {
        userService.setUserImageAsDefault(id);
        return new RedirectView("/userList");
    }

    @PostMapping("/saveUser/{id}")
    public RedirectView changeUserDetails(@PathVariable("id") Long id,@RequestParam String password, Model model, User user) {
        User userById = userService.findUserById(id);
        model.addAttribute("givenUser", userById);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userService.editUser(user);
        return new RedirectView("/userList");
    }


}
