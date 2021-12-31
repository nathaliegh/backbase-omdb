package com.backbase.omdb.movie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @version 0.0.1
 * @author NG
 *
 *  LoginController
 *
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@ApiIgnore
public class LoginController {

    @GetMapping
    public ModelAndView login(Model model) {
        return  new ModelAndView("login");
    }
}
