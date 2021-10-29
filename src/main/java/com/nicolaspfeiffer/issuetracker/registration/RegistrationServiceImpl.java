package com.nicolaspfeiffer.issuetracker.registration;

import com.nicolaspfeiffer.issuetracker.auth.AppUser;
import com.nicolaspfeiffer.issuetracker.auth.AppUserService;
import com.nicolaspfeiffer.issuetracker.recaptcha.CaptchaService;
import com.nicolaspfeiffer.issuetracker.userprofile.UserProfile;
import com.nicolaspfeiffer.issuetracker.userprofile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AppUserService appUserService;
    private final UserProfileRepository userProfileRepository;
    private final CaptchaService captchaService;

    @Override
    public String register(RegistrationRequest request) {
        if (request.getFirstName().length() == 0 |
                request.getLastName().length() == 0 |
                request.getEmail().length() == 0 |
                request.getPassword().length() == 0 |
                request.getCaptcha().length() == 0
        ) {
            // TODO: better message
            throw new IllegalArgumentException("required parameter cannot be empty string");
        }

        if (!captchaService.isValidCaptcha(request.getCaptcha())) {
            throw new IllegalArgumentException("captcha invalid");
        }

        if (appUserService.existsUser(request.getEmail())) {
            throw new IllegalStateException("email already taken");
        }

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
        appUserService.saveUser(user);

        return null;
    }
}
