package com.cwhite56.amtrec.services;

import java.util.List;

import com.cwhite56.amtrec.controllers.UserController.NewUserRequest;
import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.dtos.UserDto;

public interface UserService {
    //POST
    public UserDto createUser(NewUserRequest request);

    public SpellListDto createOrUpdateSpellList(String username, SpellListDto spellList);

    //GET
    public UserDto getUser(String username);
    public List<UserDto> getAllUsers();

    public SpellListDto getSpellList(String username, String title);

    //DELETE
    public void deleteUser(String username);

    public void deleteSpellList(String username, String title);

    public boolean userExists(String username);

    public boolean spellListExists(String title);

    

    

}
