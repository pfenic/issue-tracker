package com.nicolaspfeiffer.issuetracker.AppUser;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/user/all")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(appUserService.getUsers());
    }

    @PostMapping(path = "/user/add", consumes = "application/json")
    public ResponseEntity<AppUser> addUser(@RequestBody SignUpUserData signUpUserData) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/add").toUriString());
        AppUser user = new AppUser(
                signUpUserData.getFirstName(),
                signUpUserData.getLastName(),
                signUpUserData.getEmail(),
                signUpUserData.getPassword()
        );
        return ResponseEntity.created(uri).body(appUserService.saveUser(user));
    }
}

@Data
class SignUpUserData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
