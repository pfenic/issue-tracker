package com.nicolaspfeiffer.issuetracker.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/self")
    public ResponseEntity<UserProfile> getSelf(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok().body(userProfileService.getUserProfile(email));
    }
}
