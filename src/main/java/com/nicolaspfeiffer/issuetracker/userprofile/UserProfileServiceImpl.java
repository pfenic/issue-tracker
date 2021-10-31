package com.nicolaspfeiffer.issuetracker.userprofile;

import com.nicolaspfeiffer.issuetracker.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfile getUserProfile(Long id) {
        return userProfileRepository.getById(id);
    }

    @Override
    public UserProfile getUserProfile(String email) {
        return userProfileRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("user profile with this email does not exist")
        );
    }

    @Override
    @Transactional
    public UserProfile addProject(UserProfile profile, Project project) {
        profile.getProjects().add(project);
        return profile;
    }
}
