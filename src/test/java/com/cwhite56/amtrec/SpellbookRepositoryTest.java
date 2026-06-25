package com.cwhite56.amtrec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cwhite56.amtrec.domain.Spellbook;
import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.repositories.SpellbookRepository;
import com.cwhite56.amtrec.repositories.UserRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SpellbookRepositoryTest {

    @Autowired
    SpellbookRepository underTestSpellbook;

    @Autowired 
    UserRepository underTestUser;

    @Test
    public void testThatSpellbookCanBeCreatedAndRecalled() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

        underTestUser.save(user1);

        Spellbook spellbook1 = Spellbook.builder()
            .user(user1)
            .build();
        
    
        underTestSpellbook.save(spellbook1);

        assertThat(underTestSpellbook.findById(user1.getUsername())).isPresent();
    }
    
}
