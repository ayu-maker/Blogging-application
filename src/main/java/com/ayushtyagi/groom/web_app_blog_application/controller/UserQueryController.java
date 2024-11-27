package com.ayushtyagi.groom.web_app_blog_application.controller;

import com.ayushtyagi.groom.web_app_blog_application.entity.userQuery;
import com.ayushtyagi.groom.web_app_blog_application.services.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserQueryController {
    @Autowired
    private UserQueryService userQueryService;

    @PostMapping("/submitmessage")
    public String contactMessaget(@ModelAttribute userQuery message, Model model){
        userQueryService.saveMessage(message);
        model.addAttribute("successmessage","Message sent successfully");
        return "contact";
    }
    @PostMapping("/processMessage")
    public String contactMessage(@ModelAttribute userQuery message, Model model){
        userQueryService.saveMessage(message);
        model.addAttribute("successmessage","Message sent successfully");
        return "userauth/authcontact";
    }
}
