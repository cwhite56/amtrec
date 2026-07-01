package com.cwhite56.amtrec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.entities.Spellbook;
import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, SpellbookService spellbookService) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        Spellbook spellbook = Spellbook.builder()
            .id(user.getUsername())
            .user(user)
            .spellListCollection(new ArrayList<>())
            .build();
        
        user.setSpellbook(spellbook);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    @Override
    public User updateUser(User user, String update, char flag) {
        if (flag == 'u') user.setUsername(update);
        else if (flag == 'p') user.setPassword(update);

        else System.out.println("Invalid update flag"); 
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }
    
}
