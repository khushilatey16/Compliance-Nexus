package com.nexus.backend.service;

import com.nexus.backend.entity.User;
import com.nexus.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPreferenceService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersWithPreferences(Integer ministryId, Integer industryId, Integer categoryId, Integer stateId) {
        return userRepository.findByPreferences(ministryId, industryId, categoryId, stateId);
    }
}

