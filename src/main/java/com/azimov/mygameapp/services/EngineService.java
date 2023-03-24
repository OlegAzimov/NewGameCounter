package com.azimov.mygameapp.services;

import com.azimov.mygameapp.models.Game;
import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.models.PlayedGame;
import com.azimov.mygameapp.models.Score;
import com.azimov.mygameapp.repositories.GameUserRepository;
import com.azimov.mygameapp.repositories.GamesRepository;
import com.azimov.mygameapp.repositories.PlayedGameRepository;
import com.azimov.mygameapp.repositories.ScoreRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class EngineService {
    private final GameUserRepository gameUserRepository;
    private final GamesRepository gamesRepository;
    private final PlayedGameRepository playedGameRepository;
    private final ScoreRepository scoreRepository;
    private final DecimalFormat dF = new DecimalFormat("#.##");


    @Autowired
    public EngineService(GameUserRepository gameUserRepository, GamesRepository gamesRepository, PlayedGameRepository playedGameRepository, ScoreRepository scoreRepository) {
        this.gameUserRepository = gameUserRepository;
        this.gamesRepository = gamesRepository;
        this.playedGameRepository = playedGameRepository;
        this.scoreRepository = scoreRepository;
    }
    //для railway

    public List<GameUser> findAllGameUsers() {
        return gameUserRepository.findAll();
    }

    public List<Game> findAllGames() {
        return gamesRepository.findAll();
    }

    public List<PlayedGame> findAllPlayedGames() {
        List<PlayedGame> playedGames = playedGameRepository.findAll();
        playedGames.sort(Comparator.comparing(PlayedGame::getDate).reversed());
        return playedGames;
    }

    public Optional<PlayedGame> findPlayedGame(Date date, Game game, int number) {
        return playedGameRepository.findPlayedGame(date, game, number).stream().findAny();
    }

    public List<PlayedGame> findPlayedGameByGameName(Game game) {
        return playedGameRepository.findByGameName(game);
    }

    public Pair<Pair<String, Integer>, String> showGameUserScores(GameUser gameUser) {
        List<Score> listOfScores = scoreRepository.findByGameUserScore(gameUser);
        double sumOfPlaces = 0;
        for (Score score : listOfScores) {
            sumOfPlaces = sumOfPlaces + score.getPlace();
        }
        Pair<String, Integer> userAndHowManyGames = new Pair<>(gameUser.getName(), listOfScores.size()) {
            @Override
            public String toString() {
                return gameUser.getName() + "  " + "(" + listOfScores.size() + ")";
            }
        };
        return new Pair<>(userAndHowManyGames, dF.format(sumOfPlaces / listOfScores.size()));
    }

    public List<Score> findScoreByPlayedGame(PlayedGame playedGame) {
        return scoreRepository.findByOwner(playedGame);
    }

    public Pair<Pair<String, Integer>, String> showGameUserScoresByPlayedGame(GameUser gameUser, List<Score> scores) {

        List<Score> finalScore = new ArrayList<>();
        for (Score score : scores) {
            if (score.getGameUserScore().equals(gameUser)) {
                finalScore.add(score);
            }

        }
        double sumOfPlaces1 = 0;
        for (Score score : finalScore) {
            sumOfPlaces1 = sumOfPlaces1 + score.getPlace();
        }
        if (sumOfPlaces1 == 0) {
            return null;


        }
        Pair<String, Integer> userAndHowManyOneGame = new Pair<>(gameUser.getName(), finalScore.size()) {
            @Override
            public String toString() {
                return gameUser.getName() + "  " + "(" + finalScore.size() + ")";
            }
        };

        return new Pair<>(userAndHowManyOneGame, dF.format(sumOfPlaces1 / finalScore.size()));
    }

    public Game findGameByGameName(String name) {
        return gamesRepository.findGameByGameName(name);
    }


    @Transactional
    public void deleteGameById(int id) {
        gamesRepository.deleteById(id);
    }

    @Transactional
    public void savePlayedGame(PlayedGame playedGame) {
        playedGameRepository.save(playedGame);
    }

    @Transactional
    public void saveScore(Score score) {
        scoreRepository.save(score);
    }

    @Transactional
    public void saveGame(Game game) {
        gamesRepository.save(game);
    }
}
