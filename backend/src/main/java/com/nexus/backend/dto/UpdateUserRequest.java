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
    private String ministry;
    private String industry;
    private String category;
    private String state;
    private String organization;
    private String contact;


}
