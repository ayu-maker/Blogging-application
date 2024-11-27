package com.ayushtyagi.groom.web_app_blog_application.services;

import com.ayushtyagi.groom.web_app_blog_application.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getRecentPosts(int limit);
    List<PostDto> findAllPosts();
    List<PostDto> findPostsByUser();
    void createPost(PostDto postDto);
    PostDto findPostById(Long postId);
    void updatePost(PostDto postDto);
    void deletePost(Long postId);
    PostDto findPostByUrl(String postUrl);
    List<PostDto> searchPosts(String query);
}
