package com.nexus.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private boolean isAdmin;
}
