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
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/main_page")
public class MainPageController {
    private final PlayedGameValidator playedGameValidator;
    private final EngineService engineService;

    @Autowired
    public MainPageController(PlayedGameValidator playedGameValidator, EngineService engineService) {
        this.playedGameValidator = playedGameValidator;
        this.engineService = engineService;
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
                                @ModelAttribute("score") @Valid Score score, Model model3,
                                Model model1, @ModelAttribute("gameUser") GameUser gameUser,
                                Model model2, @ModelAttribute("game") Game game,
                                @RequestParam("gameUserList") Set<GameUser> gameUsers,
                                @RequestParam("place") List<Double> places,
                                Model model4, Model model5
    ) {
        model1.addAttribute("gameUsers", engineService.findAllGameUsers());
        playedGameValidator.validate(playedGame, bindingResult);
        model3.addAttribute("score", new Score());
        model2.addAttribute("games", engineService.findAllGames());
        model.addAttribute("playedGame", playedGame);
        if (gameUsers.size() < places.size() || bindingResult.hasErrors()) {
            if (gameUsers.size() < places.size()) {
                model5.addAttribute("user_error_message", "Один игрок не может иметь несколько мест");
                System.out.println("MainPageController: PLAYED GAME VALIDATE ERROR WITH MESSAGE : one user cannot have more than one place METHOD: POST URL: /addPlayedGame");
            }
            if (bindingResult.hasErrors()) {
                System.out.println("MainPageController: PLAYED GAME VALIDATE ERROR METHOD: POST URL: /addPlayedGame");
            }
            return "main_page";
        } else {
            model4.addAttribute("success_message", "Игра успешно добавлена");
            engineService.savePlayedGame(playedGame);

            for (GameUser gameUser1 : gameUsers) {
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


        }
        return "main_page";
    }

}