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
        // TODO: send 403 instead of redirect or better 401? maybe depending on logged in or not
        // http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
        http
                .authorizeRequests()
                .antMatchers("/", "/index.html", "/favicon.ico", "/logo192.png", "/static/**")
                    .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/registration")
                    .permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/registration")
                    .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/profile")
                .hasAnyRole("APPUSER")
                .antMatchers(HttpMethod.GET, "/api/v1/project**")
                    .hasAnyRole("APPUSER")
                .antMatchers("/api/v1/user/all")
                    .hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginProcessingUrl("/api/login")
                    .loginPage("/")
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                        httpServletResponse.setStatus(200);
                    })
                    .failureHandler(((httpServletRequest, httpServletResponse, authenticationException) -> {
                        httpServletResponse.setStatus(401);
                    }))
                .and()
                .rememberMe()
                    .rememberMeParameter("remember")
                .and()
                .logout()
                    .logoutUrl("/api/logout")
                    .permitAll()
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                        httpServletResponse.setStatus(200);
                    });
    }
}
