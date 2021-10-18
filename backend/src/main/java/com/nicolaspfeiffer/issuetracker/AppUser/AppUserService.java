package com.nicolaspfeiffer.issuetracker.AppUser;

import com.nicolaspfeiffer.issuetracker.AppUser.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser saveUser(AppUser user);
    AppUser getUser(String email);
    List<AppUser> getUsers();
}
