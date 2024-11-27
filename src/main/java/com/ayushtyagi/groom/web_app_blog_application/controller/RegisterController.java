package com.ayushtyagi.groom.web_app_blog_application.controller;

import com.ayushtyagi.groom.web_app_blog_application.dto.RegistrationDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.User;
import com.ayushtyagi.groom.web_app_blog_application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        RegistrationDto user=new RegistrationDto();
        model.addAttribute("user",user);

        return "register";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user, Model model, BindingResult result){

        User existingUser=userService.findByEmail(user.getEmail());
        if(existingUser!=null){
            result.rejectValue("email",null,"There is already a user with these email registered");
        }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";

    }

    @GetMapping("/login")
    public String showLoginForm(){

        return "login";
    }

}
