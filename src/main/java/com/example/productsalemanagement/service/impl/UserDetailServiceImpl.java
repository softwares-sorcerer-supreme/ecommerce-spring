package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.SecurityUser;
import com.example.productsalemanagement.entity.User;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user"));

    }
}
