package com.mac.doc.service;

import com.mac.doc.domain.Session;
import com.mac.doc.domain.User;
import com.mac.doc.domain.type.Role;
import com.mac.doc.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Resource
    private Session session;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("not exists."));

        session.setSessUserId(user.getUserId());
        session.setSessUserNm(user.getUserNm());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPasswd(), authorities);
    }

    @Override
    public User getSessionUser() {
        return User.builder().userId(session.getSessUserId()).userNm(session.getSessUserNm()).build();
    }
}
