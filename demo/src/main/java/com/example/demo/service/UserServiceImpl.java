package com.example.demo.service;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
//        if (!username.equals("admin")) {
//            throw new UsernameNotFoundException(username);
//        }
        User user = this.findByUsername(username).get(0);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    @Override
    public User create(User productEntity) {
        if (productEntity != null) {
            return userRepository.save(productEntity);
        }
        return null;
    }

    @Override
    public User update(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.getById(id);
        if (userRepository != null) {
            if (user != null) {
                userRepository.delete(user);
            }
        }
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        Optional<User> product = userRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException();
        }
        return product.get();
    }

    @Override
    public List<User> findByUsername(String username) {
        Specification<User> specification = ((root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("username"), username));
        return userRepository.findAll(specification);
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }
}
