package com.cwhite56.amtrec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cwhite56.amtrec.domain.Spellbook;
import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.repositories.UserRepository;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    
    @Test
    public void testThatUserCanBeCreatedAndRecalled() {
    
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

        Spellbook spellbook1 = Spellbook.builder()
            .id(user1.getUsername())
            .user(user1)
            .build();
        
        underTest.save(user1);
        
        assertThat(underTest.findById(user1.getUsername())).isNotNull();
        
    }
    @Test
    public void testThatUserCanBeUpdated() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

        Spellbook spellbook1 = Spellbook.builder()
            .id(user1.getUsername())
            .user(user1)
            .build();
        
        underTest.save(user1);
        user1.setUsername("Updated");
        underTest.save(user1);

        assertThat(underTest.findById(user1.getUsername())).isNotNull();
    }

    @Test
    public void testThatUserCanBeDeleted() {
    
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

        Spellbook spellbook1 = Spellbook.builder()
            .id(user1.getUsername())
            .user(user1)
            .build();
        
        underTest.save(user1);
        underTest.deleteById(user1.getUsername());
        
        assertThat(underTest.findById(user1.getUsername())).isNotPresent();
        
    }
}
