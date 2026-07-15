package com.cwhite56.amtrec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.dtos.UserDto;
import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.mappers.SpellListMapper;
import com.cwhite56.amtrec.mappers.UserMapper;
import com.cwhite56.amtrec.repositories.SpellListRepository;
import com.cwhite56.amtrec.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final SpellListRepository spellListRepository;

    private final UserMapper userMapper;

    private final SpellListMapper spellListMapper;

    public UserServiceImpl(UserRepository userRepository, SpellListRepository spellListRepository, UserMapper userMapper, SpellListMapper spellListMapper) {
        this.userRepository = userRepository;
        this.spellListRepository = spellListRepository;
        this.userMapper = userMapper;
        this.spellListMapper = spellListMapper;
    }

    @Override
    public UserDto createUser(UserDto user) {

        User newUser = userMapper.mapFrom(user);

        newUser.setSpellbook(new ArrayList<>());

        userRepository.save(newUser);

        return userMapper.mapTo(newUser);
    }

    @Override
    public SpellListDto createOrUpdateSpellList(String username, SpellListDto spellList) {

        Optional<User> foundUser = userRepository.findById(username);

        SpellList newSpellList = spellListMapper.mapFrom(spellList);
        
        newSpellList.setUser(foundUser.get());

        foundUser.get().getSpellbook().add(newSpellList);

        userRepository.save(foundUser.get()); 

        spellListRepository.save(newSpellList);

        return spellListMapper.mapTo(newSpellList);
    }

    @Override
    public UserDto getUser(String username) {

        Optional<User> foundUser = userRepository.findById(username);

        return userMapper.mapTo(foundUser.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> foundUsers = userRepository.findAll();

        return foundUsers.stream()
            .map(userMapper::mapTo)
            .toList();
    }

    @Override
    public SpellListDto getSpellList(String username, String title) {

        Optional<User> foundUser = userRepository.findById(username);

        Optional<SpellList> foundSpellList = spellListRepository.findById(title);

        // Figure this out in mapper
        foundSpellList.get().setUser(foundUser.get());

        return spellListMapper.mapTo(foundSpellList.get());
    }
    
    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }


    @Override
    public void deleteSpellList(String username, String title) {

        Optional<User> foundUser = userRepository.findById(username);

        Optional<SpellList> foundSpellList = spellListRepository.findById(title);

        foundUser.get().deleteSpellList(foundSpellList.get());

        userRepository.save(foundUser.get());
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public boolean spellListExists(String title) {
        return spellListRepository.existsById(title);
    }
}
