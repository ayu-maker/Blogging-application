package com.ayushtyagi.groom.web_app_blog_application.dto;

import com.ayushtyagi.groom.web_app_blog_application.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
  public  class PostDto {
    private Long id;
    @NotEmpty(message = "please add title")
    private String tittle;
    private String url;
    @NotEmpty(message = "please add content ")
    private String content;
    @NotEmpty(message = "please add short description")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
    private Set<CommentDto> comments;
}
