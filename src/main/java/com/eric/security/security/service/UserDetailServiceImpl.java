package com.eric.security.security.service;

import com.eric.security.exception.UserNotFoundException;
import com.eric.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.eric.security.entity.User user = userRepository.findUserByUserName(username).orElseThrow(() -> new UserNotFoundException("Not Found User!"));

        UserDetails userDetails = new User(user.getUserName(), user.getPassword(), user.getAuthorities());
        return userDetails;
    }
}
