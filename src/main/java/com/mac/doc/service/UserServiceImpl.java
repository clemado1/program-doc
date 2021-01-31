package com.mac.doc.service;

import com.mac.doc.domain.User;
import com.mac.doc.domain.type.Role;
import com.mac.doc.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) {
        return userRepository.findById(userId)
                .map(user -> createUser(userId, user))
                .orElseThrow(() -> new UsernameNotFoundException("Not Found."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPasswd(), authorities);
    }

    @Override
    public User getSessionUser() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        return userRepository.findById(userDetails.getUsername()).orElseThrow(() -> new Exception("No User Info."));
    }
}
