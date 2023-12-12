package com.nexus.backend.service;


import com.nexus.backend.config.TokenProvider;
import com.nexus.backend.dto.UpdateUserRequest;
import com.nexus.backend.entity.User;
import com.nexus.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {


    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

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

        if (req.getUsername()!= null)
            user.setUsername(req.getUsername());
        if (req.getCategory()!= null)
            user.setCategory(req.getCategory());
        if (req.getIndustry()!= null)
            user.setIndustry(req.getIndustry());
        if (req.getMinistry()!= null)
            user.setMinistry(req.getMinistry());
        if (req.getContact()!= null)
            user.setContact(req.getContact());
        if (req.getState()!= null)
            user.setState(req.getState());
        if (req.getOrganization()!= null)
            user.setOrganization(req.getOrganization());

        return userRepository.save(user);
    }

    @Override
    public User searchUser(String email) {
        User users = userRepository.findByEmail(email);
        return users;
    }
}
