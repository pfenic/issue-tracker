package com.nicolaspfeiffer.issuetracker.project;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> getProjectById(Long id);
    Optional<Project> getProjectByName(String name);

    @Query("SELECT p FROM Project p WHERE p.name like %:name%")
    List<Project> findByName(@Param("name") String name, Pageable pageable);
}
