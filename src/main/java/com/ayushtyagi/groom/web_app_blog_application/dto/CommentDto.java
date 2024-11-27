package com.ayushtyagi.groom.web_app_blog_application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @NotEmpty(message = "must not be empty")
    private String name;
    @NotEmpty(message = "please enter your email")
    private  String email;
    @NotEmpty(message = "please enter valid message")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
