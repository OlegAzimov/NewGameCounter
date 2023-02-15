package com.azimov.mygameapp.controllers;

import com.azimov.mygameapp.GameUserValidator;
import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.services.GameUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class GameUserController {
    private final GameUserValidator gameUserValidator;

    private final GameUsersService gameUsersService;

    public GameUserController(GameUserValidator gameUserValidator, GameUsersService gameUsersService) {
        this.gameUserValidator = gameUserValidator;
        this.gameUsersService = gameUsersService;
    }

    @GetMapping
    public String profilePage(Model model) {
        model.addAttribute("user", gameUsersService.getCurrentUser());
        return "profile_page";
    }

    @PostMapping
    public String editName(@ModelAttribute("user") @Valid GameUser gameUser, BindingResult bindingResult, Model model) {
        gameUserValidator.validate(gameUser, bindingResult);

        if (bindingResult.hasFieldErrors("name")) {
            System.out.println("GameUserController: NOT UNIQUE NAME ERROR METHOD: POST URL: /profile");
            model.addAttribute("gameUser", gameUser);
            return "profile_page";
        }

        gameUsersService.editName(gameUser.getName());
        GameUser gameUser1 = gameUsersService.getCurrentUser();
        model.addAttribute("user", gameUser1);
        return "profile_page";
    }
}
