package com.azimov.mygameapp.repositories;

import com.azimov.mygameapp.models.Game;
import com.azimov.mygameapp.models.PlayedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface PlayedGameRepository extends JpaRepository<PlayedGame, Integer> {
    @Query (value = "SELECT * FROM played_game WHERE date = ? and game_name_id = ? and number = ?",
    nativeQuery = true)
    Optional<PlayedGame> findPlayedGame(Date date, Game game, int number);

    List<PlayedGame> findByGameName(Game game);


}
