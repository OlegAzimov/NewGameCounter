package com.azimov.mygameapp.controllers;
import com.azimov.mygameapp.PlayedGameValidator;
import com.azimov.mygameapp.ScoreValidator;
import com.azimov.mygameapp.models.Game;
import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.models.PlayedGame;
import com.azimov.mygameapp.models.Score;
import com.azimov.mygameapp.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/main_page")
public class MainPageController {
    private final PlayedGameValidator playedGameValidator;
    private final EngineService engineService;
    private final ScoreValidator scoreValidator;

    @Autowired
    public MainPageController(PlayedGameValidator playedGameValidator, EngineService engineService, ScoreValidator scoreValidator) {
        this.playedGameValidator = playedGameValidator;
        this.engineService = engineService;
        this.scoreValidator = scoreValidator;
    }


    @GetMapping()
    public String gameForm(Model model, @ModelAttribute("gameUser") GameUser gameUser,
                           Model model2, @ModelAttribute("game") Game game,
                           Model model3) {

        model.addAttribute("gameUsers", engineService.findAllGameUsers());
        model2.addAttribute("games", engineService.findAllGames());
        model3.addAttribute("playedGame", new PlayedGame());
        return ("main_page");

    }


    @PostMapping("/addPlayedGame")
    public String addPlayedGame(@ModelAttribute("playedGame") @Valid PlayedGame playedGame, BindingResult bindingResult, Model model,
                                @ModelAttribute("score") @Valid Score score, BindingResult bindingResult1, Model model3,
                                Model model1, @ModelAttribute("gameUser") GameUser gameUser,
                                Model model2, @ModelAttribute("game") Game game,
                                @RequestParam("gameUserList") Set<GameUser> gameUsers,
                                @RequestParam("place") List<Double> places
                                ) {
        playedGameValidator.validate(playedGame, bindingResult);
        scoreValidator.validate(score, bindingResult1);
        model3.addAttribute("score", new Score());

        if (bindingResult.hasErrors() || bindingResult1.hasErrors() || gameUsers.size() < places.size()) {
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("playedGame", playedGame);
            model1.addAttribute("gameUsers", engineService.findAllGameUsers());
            model2.addAttribute("games", engineService.findAllGames());
            return "main_page";
        } else {
            engineService.savePlayedGame(playedGame);
            model1.addAttribute("gameUsers", engineService.findAllGameUsers());
            model2.addAttribute("games", engineService.findAllGames());
            for (GameUser gameUser1 : gameUsers){
                Score score1 = new Score();
                for (Double place : places) {
                    score1.setOwner(playedGame);
                    score1.setGameUserScore(gameUser1);
                    score1.setPlace(place);
                    engineService.saveScore(score1);
                    places.remove(place);
                    break;
                }
            }



            return "main_page";
        }
    }

}