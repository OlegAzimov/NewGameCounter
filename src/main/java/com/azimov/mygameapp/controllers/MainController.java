package com.azimov.mygameapp.controllers;

import com.azimov.mygameapp.services.GameUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller("/")
public class MainController {
    private final GameUsersService gameUsersService;

    public MainController(GameUsersService gameUsersService) {
        this.gameUsersService = gameUsersService;
    }

    @GetMapping()
    public String homePage(Model model) {
        model.addAttribute("userName",gameUsersService.getCurrentUser().getName());
        return "home_page";
    }

}
