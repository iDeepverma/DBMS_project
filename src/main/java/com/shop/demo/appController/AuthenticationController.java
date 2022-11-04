package com.shop.demo.appController;

import com.shop.demo.model.Employee;
import com.shop.demo.model.User;
import com.shop.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session){
        if(authenticationService.isAuthenticated(session)){
            return "redirect:/";
        }
        model.addAttribute("credentials", new Employee());
        model.addAttribute("isCorrect",true);
        model.addAttribute("isUser",true);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute Employee credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        if(authenticationService.isAuthenticated(session)){
            return "redirect:/";
        }

        int userID = credentials.getEmpID();
        String password = credentials.getPassword();
        boolean isCorrect = true;
        boolean isUser = true;
        try {
            if(authenticationService.checkCredentials(userID,password)){
                authenticationService.loginUser(session,userID);
                return "redirect:/customers";

            }
            isCorrect = false;
        }
        catch (Exception e){
            isUser = false;
        }
        model.addAttribute("credentials",new Employee());
        model.addAttribute("isCorrect",isCorrect);
        model.addAttribute("isUser",isUser);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }

    @GetMapping("/createEmployee")
    public String getEmployee(Model model, HttpSession session){
        if(authenticationService.isAdmin(session)){
            Employee employee = new Employee();
            model.addAttribute("employee", new Employee());
            return "createEmployee";
        }
        return "redirect:/login";
    }



}
