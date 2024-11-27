package com.ayushtyagi.groom.web_app_blog_application.repository;

import com.ayushtyagi.groom.web_app_blog_application.entity.userQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueryRepository extends JpaRepository<userQuery,Long> {
}
