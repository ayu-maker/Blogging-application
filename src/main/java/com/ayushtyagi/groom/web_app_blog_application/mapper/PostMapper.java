package com.ayushtyagi.groom.web_app_blog_application.mapper;

import com.ayushtyagi.groom.web_app_blog_application.dto.PostDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {
    public static PostDto mapToPostDto(Post post){
       return PostDto.builder()
                .id(post.getId())
                .tittle(post.getTittle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updateOn(post.getUpdatednOn())
               .comments(post.getComments().stream()
                       .map(comment -> CommentMapper.mapToCommentDto(comment))
                       .collect(Collectors.toSet())) // or Set if needed

               .build();

    }
    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .tittle(postDto.getTittle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatednOn(postDto.getUpdateOn())
                .build();
    }
}
