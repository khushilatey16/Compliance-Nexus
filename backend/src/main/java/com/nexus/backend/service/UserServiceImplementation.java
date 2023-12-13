package com.nexus.backend.service;


import com.nexus.backend.config.TokenProvider;
import com.nexus.backend.dto.UpdateUserRequest;
import com.nexus.backend.entity.User;
import com.nexus.backend.entity.preferences.Category;
import com.nexus.backend.entity.preferences.Industry;
import com.nexus.backend.entity.preferences.Ministry;
import com.nexus.backend.entity.preferences.State;
import com.nexus.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private MinistryRepository ministryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public User findUserById(Integer id) throws Exception {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();

        throw new Exception("User Not Found with Id " + id);
    }

    @Override
    public User findUserProfile(String jwt) throws Exception {
        String email = tokenProvider.getEmailFromToken(jwt);

        if (email == null) {
            throw new BadCredentialsException("Invalid Token");
        }
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new Exception("User Not Found");
        }
        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws Exception {
        User user = findUserById(userId);

        if (req.getUsername() != null) {
            user.setUsername(req.getUsername());
        }
        if (req.getMinistryId() != null) {
            Ministry ministry = ministryRepository.findById(req.getMinistryId())
                    .orElseThrow(() -> new Exception("Ministry not found"));
            user.setMinistry(ministry);
        }
        if (req.getIndustryId() != null) {
            Industry industry = industryRepository.findById(req.getIndustryId())
                    .orElseThrow(() -> new Exception("Industry not found"));
            user.setIndustry(industry);
        }
        if (req.getCategoryId() != null) {
            Category category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new Exception("Category not found"));
            user.setCategory(category);
        }
        if (req.getStateId() != null) {
            State state = stateRepository.findById(req.getStateId())
                    .orElseThrow(() -> new Exception("State not found"));
            user.setState(state);
        }
        if (req.getContact() != null) {
            user.setContact(req.getContact());
        }
        if (req.getOrganization() != null) {
            user.setOrganization(req.getOrganization());
        }

        return userRepository.save(user);
    }


    @Override
    public User searchUser(String email) {
        User users = userRepository.findByEmail(email);
        return users;
    }
}
