package com.nicolaspfeiffer.issuetracker.registration;

import com.nicolaspfeiffer.issuetracker.auth.AppUser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    // TODO: DEBUG REMOVE LATER
    @CrossOrigin
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AppUser> addUser(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
        /*
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveUser(user));
         */
        return ResponseEntity.ok().build();
    }
}
