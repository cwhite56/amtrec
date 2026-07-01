package com.cwhite56.amtrec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.repositories.SpellbookRepository;
import com.cwhite56.amtrec.services.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class UserServiceTest {

    @Autowired
    private UserService underTest;

    @Autowired
    private SpellbookRepository spellbookRepository;

    @Test
    void testThatUserCanBeCreatedAndSpellbookCreatedWithIt() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();


        User savedUser = underTest.createUser(user1);

        assertThat(spellbookRepository.findById(savedUser.getUsername())).isPresent();
    }

    @Test
    void testThatUserUsernameCanBeUpdated() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

        User savedUser = underTest.createUser(user1);

        String newName = "Jax";

        savedUser = underTest.updateUser(user1, newName, 'u');
        assertThat(savedUser.getUsername()).isEqualTo(newName);

    }

     @Test
    void testThatUserPasswordCanBeUpdated() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

        User savedUser = underTest.createUser(user1);

        String newPassword = "newpassword";

        savedUser = underTest.updateUser(user1, newPassword, 'u');
        assertThat(savedUser.getUsername()).isEqualTo(newPassword);

    }

    @Test
    void testThatUserCanBeDeletedAndItsSpellbook() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();


        User savedUser = underTest.createUser(user1);
        underTest.deleteUser(user1);

        assertThat(spellbookRepository.findById(savedUser.getUsername())).isNotPresent();
    }

        


    
}
