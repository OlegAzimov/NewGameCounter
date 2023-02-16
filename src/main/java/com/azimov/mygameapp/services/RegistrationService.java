package com.azimov.mygameapp.services;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.repositories.GameUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final GameUserRepository gameUserRepository;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, GameUserRepository gameUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.gameUserRepository = gameUserRepository;
    }

    @Transactional
    public void register(GameUser gameUser) {
        gameUser.setPassword(passwordEncoder.encode(gameUser.getPassword()));
        gameUserRepository.save(gameUser);
    }
}
