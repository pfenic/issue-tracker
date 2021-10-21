package com.nicolaspfeiffer.issuetracker.AppUser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(email);
        if (user == null) {
            // TODO: fix message string
            throw new UsernameNotFoundException("User not found in the database");
        }
        // TODO: add roles
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // TODO: DEBUG REMOVE LATER
        authorities.add(new SimpleGrantedAuthority("ROLE_APPUSER"));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(user);
    }

    @Override
    public AppUser getUser(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }
}
