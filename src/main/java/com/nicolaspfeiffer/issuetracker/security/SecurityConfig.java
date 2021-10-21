package com.nicolaspfeiffer.issuetracker.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable(); // TODO enable
        /*
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         */
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/**", "/js/**", "/static/**")
                    .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user")
                    .permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/user")
                    .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/user")
                    .hasAnyRole("APPUSER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginProcessingUrl("/api/login")
                    .loginPage("/")
                    .permitAll()
                    .defaultSuccessUrl("/api/v1/user", true);
                    //.defaultSuccessUrl("/", true);
                    //.loginPage("/login")
                    //.usernameParameter("username")
                    //.passwordParameter("password")
    }
}
