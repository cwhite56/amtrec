package com.cwhite56.amtrec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.repositories.SpellListRepository;
import com.cwhite56.amtrec.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private SpellListRepository spellListRepository;

    public UserServiceImpl(UserRepository userRepository, SpellListRepository spellListRepository) {
        this.userRepository = userRepository;
        this.spellListRepository = spellListRepository;
    }

    @Override
    public User createUser(User user) {
        user.setSpellbook(new ArrayList<>());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public SpellList createOrUpdateSpellList(Optional<User> foundUser, SpellList newSpellList) {

        User user = foundUser.get();
        
        newSpellList.setUser(user);

        user.getSpellbook().add(newSpellList);

        userRepository.save(user); 

        return spellListRepository.save(newSpellList);
    }

    @Override
    public Optional<SpellList> getSpellList(Optional<User> foundUser, String title) {

        Optional<SpellList> foundSpellList = spellListRepository.findById(title);

        if(foundSpellList.isPresent()) foundSpellList.get().setUser(foundUser.get());

        return foundSpellList;
    }

    @Override
    public void deleteSpellList(Optional<User> foundUser, Optional<SpellList> foundSpellList) {

        User user = foundUser.get();

        user.deleteSpellList(foundSpellList.get());

        userRepository.save(user);
    }
}
