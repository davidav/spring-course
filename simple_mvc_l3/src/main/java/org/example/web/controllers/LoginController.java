package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.LoginService;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping(value = "/login")
    public ModelAndView login(Model model) {
        logger.info("GET /login returns login_page ");
        model.addAttribute("loginForm", new LoginForm());
        return new ModelAndView("login_page");
    }


    @PostMapping("/books/shelf")
    public String authenticate(LoginForm loginForm) {
        if (loginService.authenticate(loginForm)) {
            return "redirect:/books/shelf";
        } else {
            return "redirect:/login";
        }
    }

}
