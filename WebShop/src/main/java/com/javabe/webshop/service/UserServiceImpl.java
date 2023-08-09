package com.javabe.webshop.service;

import com.javabe.webshop.entity.UserEntity;
import com.javabe.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity productEntity) {
        if (productEntity != null) {
            return userRepository.save(productEntity);
        }
        return null;
    }

    @Override
    public UserEntity update(Long id, UserEntity userEntity) {
        userEntity.setId(id);
        return userRepository.save(userEntity);
    }

    @Override
    public void delete(Long id) {
        UserEntity userEntity = userRepository.getById(id);
        if (userRepository != null) {
            if (userEntity != null) {
                userRepository.delete(userEntity);
            }
        }
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<UserEntity> product = userRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException();
        }
        return product.get();
    }

    @Override
    public List<UserEntity> findByUsername(String username) {
        Specification<UserEntity> specification = ((root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("username"), username));
        return userRepository.findAll(specification);
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }
}
