package com.nicolaspfeiffer.issuetracker;

import com.nicolaspfeiffer.issuetracker.auth.AppUser;
import com.nicolaspfeiffer.issuetracker.auth.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IssueTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner run(AppUserService appUserService) {
        return args -> {
            appUserService.saveUser(new AppUser(
                    "nico@pe.com",
                    "1234",
                    true,
                    true,
                    true,
                    true)
            );
        };
    }
}
