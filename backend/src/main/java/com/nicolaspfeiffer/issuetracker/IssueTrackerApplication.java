package com.nicolaspfeiffer.issuetracker;

import com.nicolaspfeiffer.issuetracker.AppUser.AppUser;
import com.nicolaspfeiffer.issuetracker.AppUser.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class IssueTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerApplication.class, args);
	}

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(AppUserService appUserService) {
        return args -> {
            appUserService.saveUser(new AppUser("nico", "pe", "nico@pe.com", "1234"));
        };
    }
}
