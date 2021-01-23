package com.mac.doc.service;

import com.mac.doc.domain.User;
import com.mac.doc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void getUserTest() {
        User user = User.builder().userId("clemado1").userNm("clema").build();

        userRepository.save(user);

        User newUser = userRepository.findById("clemado1").get();
        assertEquals(user.getUserNm(), newUser.getUserNm());
    }

}