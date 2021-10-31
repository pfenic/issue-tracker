package com.nicolaspfeiffer.issuetracker.project;

import com.nicolaspfeiffer.issuetracker.userprofile.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        return ResponseEntity.ok().body(projectService.getProject(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Project> newProject(@RequestBody Project project, Principal principal) {
        var user = userProfileService.getUserProfile(principal.getName());
        project.setOwner(user);
        System.out.println(project);
        return ResponseEntity.ok().body(projectService.saveProject(project));
    }

    @GetMapping("/{name}/exists")
    public ResponseEntity<Boolean> existsProjectByName(@PathVariable String name) {
        return ResponseEntity.ok().body(projectService.existsProject(name));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getProjectsByName(@RequestParam String name) {
        return ResponseEntity.ok().body(projectService.findProjectsByName(name));
    }
}
