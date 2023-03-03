package com.azimov.mygameapp.controllers;

import com.azimov.mygameapp.services.EngineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {
    private final EngineService engineService;

    public HistoryController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("games", engineService.findAllPlayedGames());
        return "history_page";
    }
}
