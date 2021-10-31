package com.nicolaspfeiffer.issuetracker.project;

import java.util.List;

public interface ProjectService {
    Project getProject(Long id);
    Project getProject(String name);
    Boolean existsProject(Long id);
    Boolean existsProject(String name);
    Project saveProject(Project project);
    List<Project> findProjectsByName(String name);
}
