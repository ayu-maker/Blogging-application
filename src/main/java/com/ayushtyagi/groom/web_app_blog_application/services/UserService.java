package com.ayushtyagi.groom.web_app_blog_application.services;

import com.ayushtyagi.groom.web_app_blog_application.dto.RegistrationDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
}
