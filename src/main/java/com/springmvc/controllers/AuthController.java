package com.springmvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class AuthController {
    @RequestMapping("/")
    private ModelAndView mainPage(){
        return new ModelAndView("redirect:login");
    }

    @RequestMapping("/home")
    private ModelAndView homePage(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("login",session.getAttribute("login"));

        return modelAndView;
    }

    @RequestMapping("/login")
    private ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    private ModelAndView login(@RequestParam("login") String login, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute("login", login);
        modelAndView.setViewName("redirect:home");

        return modelAndView;
    }

    @GetMapping("/logout")
    private ModelAndView logout(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:login");
        request.getSession().invalidate();
        return modelAndView;
    }

}
