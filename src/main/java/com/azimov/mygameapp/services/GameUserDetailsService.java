package com.azimov.mygameapp.services;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.repositories.GameUserRepository;
import com.azimov.mygameapp.security.GameUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameUserDetailsService implements UserDetailsService {
    private final GameUserRepository gameUserRepository;
    @Autowired
    public GameUserDetailsService(GameUserRepository gameUserRepository) {
        this.gameUserRepository = gameUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<GameUser> gameUser = gameUserRepository.findByUsername(username);

        if (gameUser.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new GameUserDetails(gameUser.get());
    }
}
