package com.cwhite56.amtrec.services;

import java.util.List;
import java.util.Optional;

import com.cwhite56.amtrec.domain.entities.User;

public interface UserService {
    //POST
    public User createUser(User user);

    //GET
    public Optional<User> getUser(String username);

    //PUT
    public User updateUser(User user, String update, char flag);

    //DELETE
    public void deleteUser(String username);

    public List<User> getAllUsers();

}
