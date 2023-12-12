package com.nexus.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String email;
    private String password;
    private boolean isAdmin;

    private String ministry;
    private String industry;
    private String category;
    private String state;

    private String organization;
    private String contact;


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
