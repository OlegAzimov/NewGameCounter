package com.azimov.mygameapp.controllers;

import com.azimov.mygameapp.GameUserValidator;
import com.azimov.mygameapp.models.GameUser;
import com.azimov.mygameapp.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final GameUserValidator gameUserValidator;
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(GameUserValidator gameUserValidator, RegistrationService registrationService) {
        this.gameUserValidator = gameUserValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("gameUser")GameUser gameUser){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("gameUser") @Valid GameUser gameUser,
                                      BindingResult bindingResult, Model model){
        gameUserValidator.validate(gameUser, bindingResult);

        if(bindingResult.hasErrors()){
            System.out.println("BINDING RESULT ERROR");
            model.addAttribute("gameUser", gameUser);
            return "/auth/registration";}
        registrationService.register(gameUser);

        return "redirect:/auth/login";


    }
}
