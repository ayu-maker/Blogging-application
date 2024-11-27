package com.ayushtyagi.groom.web_app_blog_application.security;

import com.ayushtyagi.groom.web_app_blog_application.entity.User;
import com.ayushtyagi.groom.web_app_blog_application.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
   public CustomUserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if(user!=null){
            org.springframework.security.core.userdetails.User authenticatedUser=new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map((role)-> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
            return authenticatedUser;
        }
        else{
            throw new UsernameNotFoundException("Invalid username or password");
        }

    }
}
