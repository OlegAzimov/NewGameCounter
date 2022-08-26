package com.azimov.mygameapp;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.services.GameUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class GameUserValidator implements Validator {
    private final GameUsersService gameUsersService;
    @Autowired
    public GameUserValidator(GameUsersService gameUsersService) {
        this.gameUsersService = gameUsersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        GameUser gameUser = (GameUser) target;
        if (gameUsersService.findUserByUsername(gameUser.getUsername()) !=null) {

            errors.rejectValue("username", "", "Пользователь с таким именем уже существует");


        }
    }
}
