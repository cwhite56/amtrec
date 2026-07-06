package com.cwhite56.amtrec.services;

import java.util.List;
import java.util.Optional;

import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.User;

public interface UserService {
    //POST
    public User createUser(User user);

    public SpellList createOrUpdateSpellList(Optional<User> foundUser, SpellList newSpellList);

    //GET
    public Optional<User> getUser(String username);
    public List<User> getAllUsers();

    public Optional<SpellList> getSpellList(Optional<User> foundUser, String title);

    //PUT

    //DELETE
    public void deleteUser(String username);

    public void deleteSpellList(Optional<User> foundUSer, String title);

    

    

}
