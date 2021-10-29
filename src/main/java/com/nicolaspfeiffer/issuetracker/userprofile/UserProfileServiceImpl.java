package com.nicolaspfeiffer.issuetracker.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
