package com.azimov.mygameapp.services;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.repositories.GameUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GameUsersService {
    private final GameUserRepository gameUserRepository;
    @Autowired
    public GameUsersService(GameUserRepository gameUserRepository) {
        this.gameUserRepository = gameUserRepository;
    }
    public Optional<GameUser> findUserByUsername(GameUser gameUser){
        return gameUserRepository.findByUsername(gameUser.getUsername());
    }
}
