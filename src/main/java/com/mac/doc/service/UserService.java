package com.mac.doc.service;

import com.mac.doc.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User getSessionUser();
}
