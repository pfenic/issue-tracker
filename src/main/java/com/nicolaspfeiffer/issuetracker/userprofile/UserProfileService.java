package com.nicolaspfeiffer.issuetracker.userprofile;

import com.nicolaspfeiffer.issuetracker.project.Project;

public interface UserProfileService {
    UserProfile getUserProfile(Long id);
    UserProfile getUserProfile(String email);
    UserProfile addProject(UserProfile profile, Project project);
}
