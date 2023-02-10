package com.azimov.mygameapp.controllers;

import com.azimov.mygameapp.AddToGameListValidator;
import com.azimov.mygameapp.models.Game;
import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.services.EngineService;
import com.azimov.mygameapp.services.GameUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class AddToGameListController {
    private final GameUsersService gameUsersService;
    private final EngineService engineService;
    private final AddToGameListValidator addToGameListValidator;

    @Autowired
    public AddToGameListController(GameUsersService gameUsersService, EngineService engineService, AddToGameListValidator addToGameListValidator) {
        this.gameUsersService = gameUsersService;
        this.engineService = engineService;
        this.addToGameListValidator = addToGameListValidator;
    }

    @GetMapping("/add_game_page")
    public String showAddGamePage(Model model, Model model2) {
        GameUser gameUser = gameUsersService.getCurrentUser();
        model.addAttribute("game", new Game());
        if (Objects.equals(gameUser.getRole(), "ADMIN")) {
            model2.addAttribute("games", engineService.findAllGames());
        }
        return "add_game_page";
    }

    @PostMapping("/new_game")
    public String saveNewGame(@ModelAttribute("game") @Valid Game game, BindingResult bindingResult, Model model,
                              Model model2) {
        addToGameListValidator.validate(game, bindingResult);
        if (bindingResult.hasErrors()) {
            GameUser gameUser = gameUsersService.getCurrentUser();
            model.addAttribute("game", game);
            if (Objects.equals(gameUser.getRole(), "ADMIN")) {
                model2.addAttribute("games", engineService.findAllGames());
            }
            System.out.println("BINDING RESULT ERROR");
            return "add_game_page";
        }


        engineService.saveGame(game);
        return "redirect:/add_game_page";


    }

    @PostMapping("/delete_game")
    public String deleteGame(@RequestParam(value = "games", required = false) List<Integer> ids) {
        for (int id : ids) {
            engineService.deleteGameById(id);
        }
        return "redirect:/add_game_page";
    }
}
