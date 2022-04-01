package com.task.init.services;

import com.task.init.models.dtos.CustomUserDetails;
import com.task.init.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("CustomUserDetailsService")
public class AuthenticationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found")));
    }
}
