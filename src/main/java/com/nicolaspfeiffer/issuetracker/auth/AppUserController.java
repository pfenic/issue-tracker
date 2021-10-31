package com.nicolaspfeiffer.issuetracker.auth;

import com.nicolaspfeiffer.issuetracker.userprofile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

// TODO: DEBUG REMOVE LATER
@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/self")
    public ResponseEntity<AppUser> getSelf(Principal principal) {
        String email = principal.getName();
        var user = appUserService.getUser(email);
        user.setPassword(null);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getUsers() {
        var users = appUserService.getUsers();
        users.forEach(appUser -> appUser.setPassword(null));
        return ResponseEntity.ok().body(users);
    }
}
