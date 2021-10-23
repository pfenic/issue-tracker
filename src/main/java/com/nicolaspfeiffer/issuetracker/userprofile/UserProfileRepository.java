package com.nicolaspfeiffer.issuetracker.userprofile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByEmail(String email);

    @Query("SELECT u FROM UserProfile u WHERE u.firstName = :firstName AND u.lastName = :lastName")
    List<UserProfile> findByName(String firstName, String lastName);
}
