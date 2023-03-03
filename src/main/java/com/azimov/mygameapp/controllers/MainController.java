package com.azimov.mygameapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class MainController {

    @GetMapping
    public String homePage() {
        return "home_page";
    }

    @GetMapping("/error")
    public String handleNotFoundError() {
        return "not_found";
    }

}
