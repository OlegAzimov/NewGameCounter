package com.azimov.mygameapp;

import com.azimov.mygameapp.models.PlayedGame;
import com.azimov.mygameapp.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

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
        int number = playedGame.getNumber();
        if (engineService.findPlayedGame(playedGame.getDate(), playedGame.getGameName(), number).isPresent()) {
            errors.rejectValue("number", "", "Такая игра уже существует");
            System.out.println("game " + playedGame.getGameName().getGameName() + " date: " + playedGame.getDate() + " number: " + playedGame.getNumber() + " already exists");
        }
        if (number > 1) {
            number--;
            if (engineService.findPlayedGame(playedGame.getDate(), playedGame.getGameName(), number).isEmpty()) {
                errors.rejectValue("number", "", "Добавьте предыдущую игру");
                System.out.println("no previous game found");
            }
        }
    }

    public boolean validate(List<Double> places, List<Double> scores) {
        return places.size() != 0 && scores.size() != 0;
    }
}
