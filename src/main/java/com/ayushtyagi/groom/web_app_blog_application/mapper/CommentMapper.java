package com.ayushtyagi.groom.web_app_blog_application.mapper;

import com.ayushtyagi.groom.web_app_blog_application.dto.CommentDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.Comment;

public class CommentMapper
{
    public static CommentDto mapToCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }
    //convert comment dto to comment entity
    public static Comment mapToComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn())
                .build();
    }
}
