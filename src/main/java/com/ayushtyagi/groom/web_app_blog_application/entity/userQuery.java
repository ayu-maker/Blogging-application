package com.ayushtyagi.groom.web_app_blog_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "userQuery")
public class userQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String message;

}
