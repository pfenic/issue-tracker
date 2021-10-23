package com.nicolaspfeiffer.issuetracker.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO: fix message string
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found in the database")
                );
    }

    @Override
    public AppUser saveUser(AppUser user) {
        // TODO: add roles
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(user);
    }

    @Override
    public AppUser getUser(String email) {
        return appUserRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public AppUser getUser(Long id) {
        return appUserRepository.getById(id);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }
}
