package com.azimov.mygameapp;

import com.azimov.mygameapp.models.Game;
import com.azimov.mygameapp.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddToGameListValidator implements Validator {
    private final EngineService engineService;

    @Autowired
    public AddToGameListValidator(EngineService engineService) {
        this.engineService = engineService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Game game = (Game) target;
        if (engineService.findGameByGameName(game.getGameName()) != null) {
            errors.rejectValue("gameName", "", "Эта игра уже занесена в список");
            System.out.println("game " + game.getGameName() + " is already listed");
        }

    }
}
