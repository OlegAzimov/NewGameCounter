package com.azimov.mygameapp;

import com.azimov.mygameapp.models.Score;
import com.azimov.mygameapp.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ScoreValidator implements Validator {
    private final EngineService engineService;

    @Autowired
    public ScoreValidator(EngineService engineService) {
        this.engineService = engineService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Score.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Score score = (Score) target;

    }
}
