package com.cwhite56.amtrec;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.repositories.SpellListRepository;
import com.cwhite56.amtrec.repositories.UserRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SpellListRepositoryTest {

    @Autowired
    SpellListRepository underTest;

    @Autowired 
    UserRepository userRepository;

    @Test
    @Transactional
    void testThatSpellListCanBeCreatedAndRecalled() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .spellbook(new ArrayList<>())
            .build();

        SpellList spellList = SpellList.builder()
            .title("wizard")
            .user(user1)
            .spentPoints(new ArrayList<>())
            .build();

        user1.getSpellbook().add(spellList);
    
        userRepository.save(user1);

        Optional<SpellList> savedSpellList = underTest.findById("wizard");
        assertThat(savedSpellList).isPresent();

        assertThat(savedSpellList.get().getUser()).isNotNull();
        assertThat(savedSpellList.get().getUser().getUsername()).isEqualTo("Cameron");
        
    }

    @Test
    @Transactional
    void testThatSpellListCanBeUpdated() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .spellbook(new ArrayList<>())
            .build();

        SpellList spellList = SpellList.builder()
            .title("wizard")
            .user(user1)
            .spentPoints(new ArrayList<>())
            .build();

        user1.getSpellbook().add(spellList);
        
        userRepository.save(user1);
        
        spellList.setTitle("bard");
        
        userRepository.save(user1);

        Optional<SpellList> savedSpellList = underTest.findById("bard");

        assertThat(savedSpellList).isPresent();

        assertThat(savedSpellList.get().getUser()).isNotNull();
        assertThat(savedSpellList.get().getUser().getUsername()).isEqualTo("Cameron");
        
    }

    @Test
    @Transactional
    void testThatSpellListCanBeDeleted() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .spellbook(new ArrayList<>())
            .build();
      
        SpellList spellList = SpellList.builder()
            .title("wizard")
            .user(user1)
            .spentPoints(new ArrayList<>())
            .build();

        user1.getSpellbook().add(spellList);

        userRepository.save(user1);

    
        user1.getSpellbook().remove(spellList);
        underTest.delete(spellList);

        userRepository.save(user1);


        Optional<SpellList> savedSpellList = underTest.findById("wizard");
        Optional<User> savedUser = userRepository.findById(user1.getUsername());

        assertThat(savedSpellList).isNotPresent();
        assertThat(savedUser.get().getSpellbook()).isEmpty();

    }
}
