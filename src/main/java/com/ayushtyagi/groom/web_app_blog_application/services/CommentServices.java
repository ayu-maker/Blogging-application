package com.ayushtyagi.groom.web_app_blog_application.services;

import com.ayushtyagi.groom.web_app_blog_application.dto.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentServices {
    void createComment(String Url, CommentDto commentDto);
    List<CommentDto> findAllComment();
    void deleteComment(Long commentId);

    List<CommentDto> findCommentsByPost();

}
