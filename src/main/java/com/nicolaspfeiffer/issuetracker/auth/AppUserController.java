package com.nicolaspfeiffer.issuetracker.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    // TODO: DEBUG REMOVE LATER
    @CrossOrigin
    @GetMapping("/self")
    public ResponseEntity<AppUser> getSelf() {
        // TODO userID
        return ResponseEntity.ok().body(appUserService.getUser(0L));
    }

    // TODO: DEBUG REMOVE LATER
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getUsers() {
        var users = appUserService.getUsers();
        users.forEach(appUser -> appUser.setPassword(null));
        return ResponseEntity.ok().body(users);
    }
}
