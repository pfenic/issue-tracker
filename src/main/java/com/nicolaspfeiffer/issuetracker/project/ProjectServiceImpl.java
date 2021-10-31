package com.nicolaspfeiffer.issuetracker.project;

import com.nicolaspfeiffer.issuetracker.userprofile.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserProfileService userProfileService;

    // TODO: better error messages
    @Override
    public Project getProject(Long id) {
        return projectRepository.getProjectById(id).orElseThrow(() -> new NoSuchElementException("No element with this id"));
    }

    @Override
    public Project getProject(String name) {
        return projectRepository.getProjectByName(name).orElseThrow(() -> new NoSuchElementException("No element with this id"));
    }

    @Override
    public Boolean existsProject(Long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public Boolean existsProject(String name) {
        return projectRepository.getProjectByName(name).isPresent();
    }

    @Override
    public Project saveProject(Project project) {
        if (project.getName().equals("")) {
            throw new IllegalArgumentException("project needs a name");
        }
        project.getUsers().add(project.getOwner());
        projectRepository.save(project);
        userProfileService.addProject(project.getOwner(), project);
        return project;
    }

    @Override
    public List<Project> findProjectsByName(String name) {
        return projectRepository.findByName(name, PageRequest.of(0, 10));
    }
}
