package com.backbase.omdb.security.service;

import com.backbase.omdb.security.entity.UserEntity;
import com.backbase.omdb.security.mapper.UserMapper;
import com.backbase.omdb.security.model.User;
import com.backbase.omdb.security.payload.LoginPayload;
import com.backbase.omdb.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    public User findUserFromLogin(String username) {
        return findUserByUsername(username)
                .map(userMapper::convertToModel)
                .orElse(null);
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public User saveUserFromLogin(LoginPayload loginPayload) {
        return Optional.of(createNewUserByUsername((loginPayload))
                ).map(userMapper::convertToModel)
                .orElse(null);
    }

    public UserEntity createNewUserByUsername(LoginPayload loginPayload) {
        var passwordEncoded = passwordEncoder.encode(loginPayload.getPassword());
        return userRepository
                .save(UserEntity.builder()
                        .username(loginPayload.getUsername())
                        .password(passwordEncoded)
                        .build());
    }


}
