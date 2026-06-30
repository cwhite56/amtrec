package com.cwhite56.amtrec.services;

import java.util.Optional;

import com.cwhite56.amtrec.domain.User;

public interface UserService {
    //POST
    public User createUser(User user);

    //GET
    public Optional<User> getUser(User user);

    //PUT
    public User updateUser(User user, String update, char flag);

    //DELETE
    public void deleteUser(User user);

}
