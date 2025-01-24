package com.tareq23.prescription.service.impl;

import com.tareq23.prescription.entity.Users;
import com.tareq23.prescription.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(username);
        List<SimpleGrantedAuthority> roles= new ArrayList<>();
        if(user.isPresent()) {
            roles.add(new SimpleGrantedAuthority("USERS"));
            return new User(user.get().getEmail(),user.get().getPassword(), roles);
        }
        throw new UsernameNotFoundException("username not found : +"+username);
    }
}
