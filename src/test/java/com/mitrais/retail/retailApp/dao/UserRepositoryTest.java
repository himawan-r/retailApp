package com.mitrais.retail.retailApp.dao;

import com.mitrais.retail.retailApp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test // this test to check if my app already connected to my DB
    @Rollback(value = false) // data in database got deleted on each unit test run, still not sure why
    public void testFindByName() {
        User user1 = new User("Jack", "Sparrow", "M");
        user1.setId(UUID.randomUUID().toString());
        userRepository.save(user1);

        List<User> user = userRepository.findByFirstNameOrLastName("Jack", "Sparrow");
        assertEquals(1, user.size());
    }

}