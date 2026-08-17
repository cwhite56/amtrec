package com.cwhite56.amtrec.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.Kingdom;
import com.cwhite56.amtrec.domain.Role;
import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.dtos.UserDto;
import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.SpellListId;
import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.mappers.SpellListMapper;
import com.cwhite56.amtrec.mappers.UserMapper;
import com.cwhite56.amtrec.repositories.SpellListRepository;
import com.cwhite56.amtrec.repositories.UserRepository;
import com.cwhite56.amtrec.domain.dtos.ClassStatsResponse;
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

        //spellListRepository.save(newSpellList);

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

        Optional<User> user = userRepository.findById(username);

        SpellListId id = new SpellListId(user.get(), title);

        Optional<SpellList> foundSpellList = spellListRepository.findById(id);

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
    public ClassStatsResponse getGlobalStats(String casterClass, Kingdom kingdom, Integer spellsPurchased) {

        List<SpellList> foundSpellLists;

        if(kingdom.name() == "none") {
            foundSpellLists = spellListRepository.findByCasterClass(casterClass);
        }
        else {
            foundSpellLists = spellListRepository.findByCasterClassAndUser_Kingdom(casterClass, kingdom);
        }
        ArrayList<SpellList> iterator = new ArrayList<>(foundSpellLists);
        double denominator = foundSpellLists.size();

        List<Double> responseInclusion = getGlobalInclusion(iterator, spellsPurchased, denominator);
        List<Double> responseAverage = getGlobalAverage(iterator, denominator);

        ClassStatsResponse res = ClassStatsResponse.builder()
            .inclusion(responseInclusion)
            .average(responseAverage)
            .build();
        
        return res;
    }

    private List<Double> getGlobalInclusion(ArrayList<SpellList> iterator, int spellsPurchased, double denominator) {
        List<Double> responseInclusion = new ArrayList<>(Collections.nCopies(53, 0.0));

        for(SpellList spell : iterator) {

            List<Integer> points = spell.getPurchasedSpells();
            ArrayList<Integer> pointsArray = new ArrayList<>(points);

            for(int i = 0; i < pointsArray.size(); i++) {

                if(pointsArray.get(i) >= spellsPurchased) {

                    responseInclusion.set(i, responseInclusion.get(i) + 1);
                }
            }
        }

        for(int i = 0; i < responseInclusion.size(); i++) {
            responseInclusion.set(i, (responseInclusion.get(i) / denominator) * 100);
        }
        return responseInclusion;
    }

    private List<Double> getGlobalAverage(ArrayList<SpellList> iterator, double denominator) {
        List<Double> responseAverage = new ArrayList<>(Collections.nCopies(53, 0.0));

        for(SpellList spell : iterator) {
            List<Integer> points = spell.getPurchasedSpells();
            ArrayList<Integer> pointsArray = new ArrayList<>(points);

            for(int i = 0; i < pointsArray.size(); i++) {
                responseAverage.set(i, responseAverage.get(i) + pointsArray.get(i));
            }
        }
        for(int i = 0; i < responseAverage.size(); i++) {
            responseAverage.set(i, responseAverage.get(i) / denominator);
        }

        return responseAverage;
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

        SpellListId id = new SpellListId(foundUser.get(), title);

        Optional<SpellList> foundSpellList = spellListRepository.findById(id);

        foundUser.get().deleteSpellList(foundSpellList.get());

        userRepository.save(foundUser.get());
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public boolean spellListExists(String username, String title) {
        
        Optional<User> foundUser = userRepository.findById(username);

        SpellListId id = new SpellListId(foundUser.get(), title);

        return spellListRepository.existsById(id);
    }
}
