package com.nexus.backend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Act {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String description;
    private LocalDateTime date;
    private Integer uploaderId;

    private String State;
    private String Ministry;
    private String Industry;
    private String Category;

    @OneToMany
    private Set<Compliance> complianceSet;

}
