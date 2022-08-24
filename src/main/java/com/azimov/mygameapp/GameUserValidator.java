package com.azimov.mygameapp;

import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.services.GameUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class GameUserValidator implements Validator {
    private final GameUserDetailsService gameUserDetailsService;

    @Autowired
    public GameUserValidator(GameUserDetailsService gameUserDetailsService) {
        this.gameUserDetailsService = gameUserDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return GameUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GameUser gameUser =(GameUser) target;
        try {
            gameUserDetailsService.loadUserByUsername(gameUser.getUsername());
        } catch (UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("username", "", "Пользователь с таким именем уже существует");



    }
}
