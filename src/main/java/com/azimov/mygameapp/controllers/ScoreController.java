package com.azimov.mygameapp.controllers;

import com.azimov.mygameapp.models.Game;
import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.models.PlayedGame;
import com.azimov.mygameapp.models.Score;
import com.azimov.mygameapp.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ScoreController {
    private final EngineService engineService;

    @Autowired
    public ScoreController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping("/score_page")
    public String showScore(Model model2,
                            Model model4) {
        model4.addAttribute("games", engineService.findAllGames());
        List<GameUser> gameUsers = engineService.findAllGameUsers();
        Map<String, String> gameUsersMap = new HashMap<>();
        for (GameUser gu : gameUsers) {
            gameUsersMap.put(engineService.showGameUserScores(gu).getKey(), engineService.showGameUserScores(gu).getValue());

        }
        Map<String, String> finalMap = new HashMap<>(gameUsersMap);
        List<Map.Entry<String, String>> entries = finalMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        model2.addAttribute("gameUsersMapsList", entries);


        return "score_page";


    }

    @PostMapping("/filter")
    public String showScoreByPlayedGames(@RequestParam(value = "gameList", required = false) List<Game> games,
                                         Model model,
                                         Model model1) {
        if(games == null){
            return "redirect:/score_page";
        }
        model.addAttribute("games", engineService.findAllGames());
        List<PlayedGame> playedGames = new ArrayList<>();
        for (Game game : games) {
            playedGames.addAll(engineService.findPlayedGameByGameName(game));
        }
        List<Score> scores = new ArrayList<>();
        for (PlayedGame playedGame : playedGames) {
            scores.addAll(engineService.findScoreByPlayedGame(playedGame));

        }
        List<GameUser> gameUsers = engineService.findAllGameUsers();
        Map<String, String> gameUsersMap = new HashMap<>();
        for (GameUser gu : gameUsers) {
            if (engineService.showGameUserScoresByPlayedGame(gu, scores) != null)
                gameUsersMap.put(engineService.showGameUserScoresByPlayedGame(gu, scores).getKey(), (engineService.showGameUserScoresByPlayedGame(gu, scores).getValue()));

        }
        Map<String, String> finalMap = new HashMap<>(gameUsersMap);
        Map<String, String> sortedFinalMap = finalMap;
        List<Map.Entry<String, String>> entries = sortedFinalMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();


        model1.addAttribute("gameUsersMapsList", entries);


        return "score_page";
    }
}
