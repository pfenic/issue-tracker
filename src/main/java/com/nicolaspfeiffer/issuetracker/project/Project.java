package com.nicolaspfeiffer.issuetracker.project;

import com.nicolaspfeiffer.issuetracker.issue.Issue;
import com.nicolaspfeiffer.issuetracker.userprofile.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Project")
@Table(
        name = "project",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "project_name_unique",
                        columnNames = "name"
                )
        }
)
public class Project {
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            generator = "project_sequence",
            strategy = SEQUENCE
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @ManyToOne(
            fetch = LAZY,
            optional = false
    )
    private UserProfile owner;

    @ManyToMany(fetch = LAZY)
    private Collection<UserProfile> users = new ArrayList<>();

    @OneToMany(fetch = LAZY)
    private Collection<Issue> issues = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }
}
