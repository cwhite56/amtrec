package com.cwhite56.amtrec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.Role;
import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.dtos.UserDto;
import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.mappers.SpellListMapper;
import com.cwhite56.amtrec.mappers.UserMapper;
import com.cwhite56.amtrec.repositories.SpellListRepository;
import com.cwhite56.amtrec.repositories.UserRepository;
import com.cwhite56.amtrec.domain.dtos.NewUserRequest;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final SpellListRepository spellListRepository;

    private final UserMapper userMapper;

    private final SpellListMapper spellListMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, SpellListRepository spellListRepository, UserMapper userMapper, SpellListMapper spellListMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.spellListRepository = spellListRepository;
        this.userMapper = userMapper;
        this.spellListMapper = spellListMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(NewUserRequest request) {

        User newUser = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .kingdom(request.getKingdom())
            .role(Role.USER)
            .build(); 

        newUser.setSpellbook(new ArrayList<>());

        userRepository.save(newUser);

        return userMapper.mapTo(newUser);
    }

    @Override
    public SpellListDto createOrUpdateSpellList(String username, SpellListDto spellList) {

        String authorizedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!authorizedUser.equals(username)) return new SpellListDto();

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
    public SpellListDto getSpellList(String title) {

        Optional<SpellList> foundSpellList = spellListRepository.findById(title);

        return spellListMapper.mapTo(foundSpellList.get());
    }

      @Override
    public List<SpellListDto> getAllUsersSpellLists(String username) {

        List<SpellList> foundSpellLists = spellListRepository.findAllByUserUsername(username);

        return foundSpellLists.stream()
            .map(spellListMapper::mapTo)
            .toList();
    }
    
    @Override
    public void deleteUser(String username) {

        String authorizedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!authorizedUser.equals(username)) return;

        userRepository.deleteById(username);
    }


    @Override
    public void deleteSpellList(String username, String title) {

        String authorizedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!authorizedUser.equals(username)) return;

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
