package com.ayushtyagi.groom.web_app_blog_application.repository;

import com.ayushtyagi.groom.web_app_blog_application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
