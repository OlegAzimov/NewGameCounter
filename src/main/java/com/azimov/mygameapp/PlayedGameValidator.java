package com.azimov.mygameapp;

import com.azimov.mygameapp.models.PlayedGame;
import com.azimov.mygameapp.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayedGameValidator implements Validator {
    private final EngineService engineService;

    @Autowired
    public PlayedGameValidator(EngineService engineService) {
        this.engineService = engineService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PlayedGame.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlayedGame playedGame = (PlayedGame) target;
        if (engineService.findPlayedGame(playedGame.getDate(), playedGame.getGameName(), playedGame.getNumber()).isPresent()) {
            errors.rejectValue("number", "", "Такая игра уже существует");
        }

    }
}
