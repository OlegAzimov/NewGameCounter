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
        validateName(gameUser.getName(), errors);
        validateUsername(gameUser.getUsername(), errors);
    }

    public void validateUsername(String username, Errors errors) {
        if (gameUsersService.findUserByUsername(username).isPresent()) {
            errors.rejectValue("username", "", "Пользователь с таким логином уже существует");
            System.out.println("user with the same username already exists");
        }
    }

    public void validateName(String name, Errors errors) {
        if (gameUsersService.findUserByName(name).isPresent()) {
            errors.rejectValue("name", "", "Данное имя уже занято");
            System.out.println("user with the same name already exists");
        }
    }
}
