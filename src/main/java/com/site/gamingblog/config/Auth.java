package com.site.gamingblog.config;

import com.site.gamingblog.model.User;
import com.site.gamingblog.service.MyUserDetailsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class Auth extends WebSecurityConfigurerAdapter {

    private  final MyUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/account", "/addCommentToTheGamePost", "/addCommentToTheNews", "/userImageDefault")
                .hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/gamesList","/addGame", "/editGame/{id}", "/userList",
                        "/deleteGame/{id}", "/newsList","/addNews", "/editNews/{id}", "/deleteNews/{id}", "/userStatus/{id}")
                .hasAnyAuthority("ADMIN")
                .and() // dopisujemy, aby kontynuować konfiguracje kolejnych blokow
                .csrf().disable() // wyłaczamy csrf, aby miec możliwość   do testowania postmanem
                .headers().frameOptions().disable()
                .and()
                .formLogin()
//                .loginPage("/login") // wskazujemy endpointa, w którym odbywać będzie się uwierzytelnianie
                .usernameParameter("username")
                .passwordParameter("password")
//                .defaultSuccessUrl("/")
//                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}

