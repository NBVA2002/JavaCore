package com.example.demo.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
  User user;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
    List<GrantedAuthority> authorities = new LinkedList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRoles()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return new BCryptPasswordEncoder().encode(user.getPassword());
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}