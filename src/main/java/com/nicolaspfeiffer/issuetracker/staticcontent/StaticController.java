package com.nicolaspfeiffer.issuetracker.staticcontent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StaticController {
    @GetMapping(path = {
            "/projects",
            "/projects/new",
            "/login",
            "/signup"
    })
    public ModelAndView projects(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("forward:/index.html");
    }
}
