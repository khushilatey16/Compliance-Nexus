package com.nexus.backend.dto;

import com.nexus.backend.entity.Compliance;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateActRequest {

    private String title;
    private String description;
    private LocalDateTime date;
    private Integer uploaderId;
    private Set<Compliance> complianceSet;
}
