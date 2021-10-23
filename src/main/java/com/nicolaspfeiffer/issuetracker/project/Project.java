package com.nicolaspfeiffer.issuetracker.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

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

    public Project(String name) {
        this.name = name;
    }
}
