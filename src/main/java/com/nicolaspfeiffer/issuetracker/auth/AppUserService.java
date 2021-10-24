package com.nicolaspfeiffer.issuetracker.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService extends UserDetailsService {
    AppUser saveUser(AppUser user);
    AppUser getUser(String email);
    AppUser getUser(Long id);
    Boolean existsUser(String email);
    Boolean existsUser(Long id);
    List<AppUser> getUsers();
}
