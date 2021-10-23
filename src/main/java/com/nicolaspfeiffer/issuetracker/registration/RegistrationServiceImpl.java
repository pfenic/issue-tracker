package com.nicolaspfeiffer.issuetracker.registration;

import com.nicolaspfeiffer.issuetracker.auth.AppUser;
import com.nicolaspfeiffer.issuetracker.auth.AppUserRepository;
import com.nicolaspfeiffer.issuetracker.project.Project;
import com.nicolaspfeiffer.issuetracker.project.ProjectRepository;
import com.nicolaspfeiffer.issuetracker.userprofile.UserProfile;
import com.nicolaspfeiffer.issuetracker.userprofile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AppUserRepository appUserRepository;
    private final UserProfileRepository userProfileRepository;

    @Override
    public String register(RegistrationRequest request) {
        if (request.getFirstName().length() == 0 |
                request.getLastName().length() == 0 |
                request.getEmail().length() == 0 |
                request.getPassword().length() == 0
        ) {
            // TODO: better message
            throw new IllegalArgumentException("required parameter cannot be empty string");
        }

        appUserRepository
                .findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new IllegalStateException("email already taken");
                });

        var user = new AppUser(
                request.getEmail(),
                request.getPassword(),
                true,
                true,
                true,
                true
        );
        var profile = new UserProfile(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
        );

        user.setProfile(profile);

        userProfileRepository.save(profile);
        appUserRepository.save(user);

        return null;
    }
}
