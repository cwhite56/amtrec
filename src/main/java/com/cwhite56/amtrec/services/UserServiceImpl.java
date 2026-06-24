package com.cwhite56.amtrec.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(User user) {
        return userRepository.findById(user.getUsername());
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteById(user.getUsername());
    }
    
}
