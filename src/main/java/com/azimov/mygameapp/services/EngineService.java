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


    @Autowired
    public EngineService(GameUserRepository gameUserRepository, GamesRepository gamesRepository, PlayedGameRepository playedGameRepository, ScoreRepository scoreRepository) {
        this.gameUserRepository = gameUserRepository;
        this.gamesRepository = gamesRepository;
        this.playedGameRepository = playedGameRepository;
        this.scoreRepository = scoreRepository;
    }

    public List<GameUser> findAllGameUsers(){
        return gameUserRepository.findAll();
    }
    public List<Game> findAllGames(){
        return gamesRepository.findAll();
    }
    public List<PlayedGame> findAllPlayedGames(){return playedGameRepository.findAll();}
    public List<Score> findAllScores(){return scoreRepository.findAll();}
    public Optional <PlayedGame> findPlayedGame(Date date, Game game, int number){
        return playedGameRepository.findPlayedGame(date, game, number).stream().findAny();
    }
    public List<PlayedGame> findPlayedGameByGameName(Game game){
        return playedGameRepository.findByGameName(game);
    }
    public List<Score> findScoreByGameUser(GameUser gameUser){
        return scoreRepository.findByGameUserScore(gameUser);
    }
    public Pair<String, String> showGameUserScores(GameUser gameUser){
        List<Score> listOfScores = scoreRepository.findByGameUserScore(gameUser);
        double sumOfPlaces = 0;
        for (Score score : listOfScores){
            sumOfPlaces = sumOfPlaces + score.getPlace();
        }
        DecimalFormat dF = new DecimalFormat("#.##");
        Pair<String, String> userScore = new Pair<>(gameUser.getUsername(),  dF.format(sumOfPlaces/listOfScores.size()));
        return userScore;
    }
    public List<Score> findScoreByPlayedGame(PlayedGame playedGame){
        return scoreRepository.findByOwner(playedGame);
    }
    public Pair<String, String> showGameUserScoresByPlayedGame(GameUser gameUser, List<Score> scores){

        List<Score> finalScore = new ArrayList<>();
        for (Score score : scores){
            if(score.getGameUserScore().equals(gameUser)){
                finalScore.add(score);
            }

        }
        double sumOfPlaces1 = 0;
        for (Score score : finalScore){
            sumOfPlaces1 = sumOfPlaces1 + score.getPlace();
        }
        if(sumOfPlaces1 == 0){
            return null;


        }
        DecimalFormat dF = new DecimalFormat("#.##");

        Pair<String, String> userScore = new Pair<>(gameUser.getUsername(),  dF.format(sumOfPlaces1/finalScore.size()) );
        return userScore;}

    public Game findGameByGameName(String name){
        return gamesRepository.findGameByGameName(name);
    }





    @Transactional
    public void deleteGameById(int id){gamesRepository.deleteById(id);}
    @Transactional
    public void savePlayedGame(PlayedGame playedGame){
        playedGameRepository.save(playedGame);
    }
    @Transactional
     public void saveScore(Score score){
        scoreRepository.save(score);
     }
    @Transactional
     public void saveGame(Game game){
        gamesRepository.save(game);
     }
}
