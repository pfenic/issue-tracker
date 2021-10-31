package com.nicolaspfeiffer.issuetracker.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Issue")
@Table(
        name = "issue"
        /*
        ,
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "project_name_unique",
                        columnNames = "name"
                )
        }
         */
)
public class Issue {
    @SequenceGenerator(
            name = "issue_sequence",
            sequenceName = "issue_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            generator = "issue_sequence",
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

    @Column(
            name = "description",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String description;
}
