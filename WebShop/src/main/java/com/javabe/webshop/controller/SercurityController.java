package com.javabe.webshop.controller;

import com.javabe.webshop.model.login.LoginRequest;
import com.javabe.webshop.model.login.LoginResponse;
import com.javabe.webshop.security.jwt.JwtAuthenticationFilter;
import com.javabe.webshop.security.jwt.JwtTokenProvider;
import com.javabe.webshop.security.service.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SercurityController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public String randomStuff() {
        return "JWT Hợp lệ mới có thể thấy được message này";
    }

    @GetMapping("/admin")
    public String adminApi(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        String userToken = "";
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            userToken = bearerToken.substring(7);
//        }
//
//        String username = tokenProvider.getUserIdFromJWT(userToken);
//        User user = userService.findByUsername(username).get(0);
//        if(user.getRoles().equals("ROLE_ADMIN")) {
//            return "Admin mới có thể thấy được message này";
//        }
        return "Chỉ ADMIN mới vào được";
    }

    @GetMapping("/current")
    public UserDetails getCurrentUser(HttpServletRequest request) {

        String userToken = jwtAuthenticationFilter.getJwtFromRequest(request);
        String username = tokenProvider.getUserIdFromJWT(userToken);
        return userDetailsService.loadUserByUsername(username);
    }
}
