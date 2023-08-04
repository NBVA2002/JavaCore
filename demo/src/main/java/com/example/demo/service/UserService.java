package com.example.demo.service;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User create(User productEntity);
    User update(Long id, User productEntity);
    void delete(Long id);
    Page<User> findAll(Pageable pageable);
    User findById(Long id);
    List<User> findByUsername(String username);

    int totalItem();
}
