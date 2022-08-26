package com.azimov.mygameapp.repositories;

import com.azimov.mygameapp.models.Game;
import com.azimov.mygameapp.models.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<GameUser, Integer> {
    GameUser findByUsername(String username);
}
