package com.ayushtyagi.groom.web_app_blog_application.repository;

import com.ayushtyagi.groom.web_app_blog_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
