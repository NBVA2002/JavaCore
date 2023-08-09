package com.javabe.webshop.service;

import com.javabe.webshop.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity create(UserEntity productEntity);
    UserEntity update(Long id, UserEntity productEntity);
    void delete(Long id);
    Page<UserEntity> findAll(Pageable pageable);
    UserEntity findById(Long id);
    List<UserEntity> findByUsername(String username);

    int totalItem();
}
