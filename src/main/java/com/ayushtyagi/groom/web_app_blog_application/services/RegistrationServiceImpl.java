package com.ayushtyagi.groom.web_app_blog_application.services;

import com.ayushtyagi.groom.web_app_blog_application.dto.RegistrationDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.Role;
import com.ayushtyagi.groom.web_app_blog_application.entity.User;
import com.ayushtyagi.groom.web_app_blog_application.repository.RoleRepository;
import com.ayushtyagi.groom.web_app_blog_application.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegistrationServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user=new User();
        user.setName(registrationDto.getFirstName()+" "+registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role=roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);


    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
