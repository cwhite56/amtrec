package com.cwhite56.amtrec;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cwhite56.amtrec.domain.Spellbook;
import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.repositories.UserRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

    private UserRepository underTest;

    @Autowired
    public UserServiceTest(UserRepository underTest) {
        this.underTest = underTest;
    }
    
    @Test
    void testThatUserCanBeSaved() {
    
        User user1 = User.builder()
            .username("Cameron")
            .password("password")
            .build();

        Spellbook spellbook1 = Spellbook.builder()
            .id(user1.getUsername())
            .user(user1)
            .build();
        
        user1.setSpellbook(spellbook1);
        
        User savedUser = underTest.save(user1);
        assertNull(savedUser);
    }
}
