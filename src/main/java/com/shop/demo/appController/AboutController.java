package com.shop.demo.appController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping({"/aboutUs"})
    public String homepage(){
        return "aboutUs";
    }
}
