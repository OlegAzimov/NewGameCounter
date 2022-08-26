package com.azimov.mygameapp.services;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GameUsersService {
    private final UserRepository userRepository;

    public GameUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GameUser findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
