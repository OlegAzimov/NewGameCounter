package com.azimov.mygameapp.services;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.repositories.GameUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional()
public class GameUsersService {
    private final GameUserRepository gameUserRepository;

    public GameUsersService(GameUserRepository gameUserRepository) {
        this.gameUserRepository = gameUserRepository;
    }

    public Optional<GameUser> findUserByUsername(String username) {
        return gameUserRepository.findByUsername(username);
    }
    public Optional<GameUser> findUserByName(String name) {
        return gameUserRepository.findByName(name);
    }

    public GameUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return gameUserRepository.findByUsername(username).get();
    }

    public void editName(String newName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        GameUser gameUser = gameUserRepository.findByUsername(username).get();
        gameUser.setName(newName);
        gameUserRepository.save(gameUser);
    }
}
