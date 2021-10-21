package com.nicolaspfeiffer.issuetracker.AppUser;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    // TODO: DEBUG REMOVE LATER
    @CrossOrigin
    @GetMapping("/user")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(appUserService.getUsers());
    }

    // TODO: DEBUG REMOVE LATER
    @CrossOrigin
    @PostMapping(path = "/user", consumes = "application/json")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveUser(user));
    }
}
