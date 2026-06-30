package com.cwhite56.amtrec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cwhite56.amtrec.domain.SpellList;
import com.cwhite56.amtrec.domain.Spellbook;
import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.repositories.SpellbookRepository;
import com.cwhite56.amtrec.repositories.UserRepository;

import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SpellbookRepositoryTest {

    @Autowired
    SpellbookRepository underTest;

    @Autowired 
    UserRepository userRepository;

    @Test
    void testThatSpellbookCanBeCreatedAndRecalled() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

       
        userRepository.save(user1);
        
        Spellbook spellbook1 = Spellbook.builder()
            .user(user1)
            .build();
        
        underTest.save(spellbook1);

        assertThat(underTest.findById(user1.getUsername())).isPresent();
    }

    @Test
    @Transactional
    void testThatSpellbookCanBeUpdated() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

       
        userRepository.save(user1);

        Spellbook spellbook1 = Spellbook.builder()
            .user(user1)
            .spellListCollection(new ArrayList<>())
            .build();

        underTest.save(spellbook1);

        SpellList spellList = SpellList.builder()
            .title("wizard")
            .spellbook(spellbook1)
            .spentPoints(new ArrayList<>())
            .build();
        
        spellbook1.getSpellListCollection().add(spellList);
        underTest.save(spellbook1);

        Optional<Spellbook> savedSpellbook = underTest.findById(user1.getUsername());

        assertThat(savedSpellbook.get().getSpellListCollection()).isNotEmpty();


        
    }
}
