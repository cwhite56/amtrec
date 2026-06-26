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

import com.cwhite56.amtrec.domain.SpellList;
import com.cwhite56.amtrec.domain.Spellbook;
import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.repositories.SpellListRepository;
import com.cwhite56.amtrec.repositories.SpellbookRepository;
import com.cwhite56.amtrec.repositories.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SpellListRepositoryTest {

    @Autowired
    SpellListRepository underTest;

    @Autowired
    SpellbookRepository spellbookRepository;

    @Autowired 
    UserRepository userRepository;

    @Test
    @Transactional
    public void testThatSpellListCanBeCreatedAndRecalled() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

       userRepository.save(user1);

        Spellbook spellbook1 = Spellbook.builder()
            .user(user1)
            .spellListCollection(new ArrayList<>())
            .build();
        
        
        spellbookRepository.save(spellbook1);

        SpellList spellList = SpellList.builder()
            .title("wizard")
            .spellbook(spellbook1)
            .spentPoints(new ArrayList<>())
            .build();

        underTest.save(spellList);

        spellbook1.getSpellListCollection().add(spellList);

        spellbookRepository.save(spellbook1);

        Optional<Spellbook> savedSpellbook = spellbookRepository.findById(user1.getUsername());
        Optional<SpellList> savedSpellList = underTest.findById("wizard");



        assertThat(savedSpellbook.get().getSpellListCollection().get(0)).isEqualTo(savedSpellList.get());
        
    }

    @Test
    @Transactional
    public void testThatSpellListCanBeUpdatedAndUpdatesReflectedInSpellbook() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

       
        userRepository.save(user1);

        Spellbook spellbook1 = Spellbook.builder()
            .user(user1)
            .spellListCollection(new ArrayList<>())
            .build();

        spellbookRepository.save(spellbook1);

        SpellList spellList = SpellList.builder()
            .title("wizard")
            .spellbook(spellbook1)
            .spentPoints(new ArrayList<>())
            .build();

        underTest.save((spellList));

        spellbook1.getSpellListCollection().add(spellList);
        spellList.setTitle("bard");

        spellbookRepository.save(spellbook1);

        underTest.save(spellList);

        Optional<Spellbook> savedSpellbook = spellbookRepository.findById(user1.getUsername());
        SpellList savedSpellListInSpellbook = savedSpellbook.get().getSpellListCollection().get(0);
        Optional<SpellList> savedSpellList = underTest.findById("bard");

        assertThat(savedSpellListInSpellbook.getTitle()).isEqualTo(savedSpellList.get().getTitle());
        
    }

    @Test
    @Transactional
    public void testThatSpellListCanBeDeleted() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

       
        userRepository.save(user1);

        Spellbook spellbook1 = Spellbook.builder()
            .user(user1)
            .spellListCollection(new ArrayList<>())
            .build();

        spellbookRepository.save(spellbook1);

        SpellList spellList = SpellList.builder()
            .title("wizard")
            .spellbook(spellbook1)
            .spentPoints(new ArrayList<>())
            .build();

        underTest.save((spellList));

        spellbook1.getSpellListCollection().add(spellList);

        underTest.delete(spellList);
        spellbook1.getSpellListCollection().remove(spellList);

        spellbookRepository.save(spellbook1);

        Optional<Spellbook> savedSpellbook = spellbookRepository.findById(user1.getUsername());

        assertThat(savedSpellbook.get().getSpellListCollection()).isEmpty();
        assertThat(underTest.findById("wizard")).isNotPresent();

    }
}
