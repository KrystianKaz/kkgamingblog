package com.site.gamingblog.service;

import com.site.gamingblog.exception.UserDisabledException;
import com.site.gamingblog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, UserDisabledException {
        User user = userService.findUserByUsername(username);
        List<GrantedAuthority> authority = getUserAuthority(user);
        return buildUserForAuthentication(user, authority);
    }

    public List<GrantedAuthority> getUserAuthority(User user) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getUserRoles())));
        return new ArrayList<>(authorities);
    }

    public UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isActive(), true, true, true, authorities);
    }
}
