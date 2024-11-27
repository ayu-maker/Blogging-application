package com.ayushtyagi.groom.web_app_blog_application.services;

import com.ayushtyagi.groom.web_app_blog_application.entity.userQuery;
import com.ayushtyagi.groom.web_app_blog_application.repository.UserQueryRepository;
import com.ayushtyagi.groom.web_app_blog_application.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private UserQueryRepository userQueryRepository;
    UserQueryService(UserQueryRepository userQueryRepository){
      this.userQueryRepository=userQueryRepository;
    }
    public void saveMessage(userQuery userQuery){
        userQueryRepository.save(userQuery);
    }
}
