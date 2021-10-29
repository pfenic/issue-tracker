package com.nicolaspfeiffer.issuetracker.userprofile;

public interface UserProfileService {
    UserProfile getUserProfile(Long id);
    UserProfile getUserProfile(String email);
}
