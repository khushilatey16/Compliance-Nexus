package com.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String username;
    private Integer ministryId;
    private Integer industryId;
    private Integer categoryId;
    private Integer stateId;
    private String contact;
    private String organization;

}

