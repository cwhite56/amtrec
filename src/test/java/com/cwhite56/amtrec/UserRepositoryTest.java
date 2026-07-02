package com.cwhite56.amtrec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cwhite56.amtrec.domain.entities.User;
import com.cwhite56.amtrec.repositories.UserRepository;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    
    @Test
    void testThatUserCanBeCreatedAndRecalled() {
    
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();
        
        underTest.save(user1);
        
        assertThat(underTest.findById(user1.getUsername())).isPresent();
        
    }
    @Test
    void testThatUserCanBeUpdated() {
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();
        
        underTest.save(user1);
        String newName = "Updated";
        user1.setUsername(newName);
        underTest.save(user1);

        assertThat(underTest.findById(newName)).isPresent();
    }

    @Test
    void testThatUserCanBeDeleted() {
    
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();
        
        underTest.save(user1);
        underTest.deleteById(user1.getUsername());
        
        assertThat(underTest.findById(user1.getUsername())).isNotPresent();
        
    }
}
