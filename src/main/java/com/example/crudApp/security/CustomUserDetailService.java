package com.example.crudApp.security;

import com.example.crudApp.entity.AppUser;
import com.example.crudApp.exception.ResourceNotFoundException;
import com.example.crudApp.repository.AppUserRespository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final AppUserRespository appUserRespository;

    public CustomUserDetailService(AppUserRespository appUserRespository){
        this.appUserRespository=appUserRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser u= appUserRespository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(List.of())
                .build();





    }



}
