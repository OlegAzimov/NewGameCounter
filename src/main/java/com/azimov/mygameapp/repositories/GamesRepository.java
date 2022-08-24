package com.azimov.mygameapp.repositories;

import com.azimov.mygameapp.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<Game, Integer> {
    Game findGameByGameName(String name);
    void deleteById(int id);
}
