package com.azimov.mygameapp.repositories;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.models.PlayedGame;
import com.azimov.mygameapp.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findByGameUserScore(GameUser gameUserScore);

    List<Score> findByOwner(PlayedGame playedGame);

}
