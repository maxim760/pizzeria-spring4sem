package com.example.pizzeria.service;

import com.example.pizzeria.entity.UserEntity;
import com.example.pizzeria.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }
    public UserEntity addUser(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + "!!username");
        UserEntity user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }
}
