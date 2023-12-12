package com.nexus.backend.service;

import com.nexus.backend.dto.UpdateUserRequest;
import com.nexus.backend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserById(Integer id) throws Exception;

    public User findUserProfile(String jwt) throws Exception;

    public User updateUser (Integer userId, UpdateUserRequest req) throws Exception;

    public User searchUser(String email);
}
