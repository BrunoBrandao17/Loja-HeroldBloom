package com.heroldbloom.heroldbloom_backend.service;

import com.heroldbloom.heroldbloom_backend.model.DonaModel;
import com.heroldbloom.heroldbloom_backend.repository.DonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DonaRepository donaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DonaModel dona = donaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        return User.builder()
                .username(dona.getEmail())
                .password(dona.getSenha())
                .roles("ADMIN")
                .build();
    }
}