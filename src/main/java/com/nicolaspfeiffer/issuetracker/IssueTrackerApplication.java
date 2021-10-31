package com.nicolaspfeiffer.issuetracker;

import com.nicolaspfeiffer.issuetracker.auth.AppUser;
import com.nicolaspfeiffer.issuetracker.auth.AppUserService;
import com.nicolaspfeiffer.issuetracker.userprofile.UserProfile;
import com.nicolaspfeiffer.issuetracker.userprofile.UserProfileRepository;
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
    CommandLineRunner run(AppUserService appUserService, UserProfileRepository userProfileRepository) {
        return args -> {
            if (!appUserService.existsUser("nico@pe.com")) {
                var user = new AppUser(
                        "nico@pe.com",
                        //"1234",
                        "nicoistderbesteduweistdas",
                        true,
                        true,
                        true,
                        true
                );
                var profile = new UserProfile(
                        "Nico",
                        "Pe",
                        "nico@pe.com"
                );

                user.setProfile(profile);

                userProfileRepository.save(profile);
                appUserService.saveUser(user);
            }
        };
    }
}
