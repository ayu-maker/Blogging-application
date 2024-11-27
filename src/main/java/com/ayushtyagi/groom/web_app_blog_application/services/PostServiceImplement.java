package com.ayushtyagi.groom.web_app_blog_application.services;

import com.ayushtyagi.groom.web_app_blog_application.dto.PostDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.Post;
import com.ayushtyagi.groom.web_app_blog_application.entity.User;
import com.ayushtyagi.groom.web_app_blog_application.mapper.PostMapper;
import com.ayushtyagi.groom.web_app_blog_application.repository.PostRepository;
import com.ayushtyagi.groom.web_app_blog_application.repository.UserRepository;
import com.ayushtyagi.groom.web_app_blog_application.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplement implements PostService{
    private PostRepository postRepository;
    private UserRepository userRepository;
    public PostServiceImplement(PostRepository postRepository,UserRepository userRepository){
        this.postRepository=postRepository;
        this.userRepository=userRepository;
    }

    @Override
    public List<PostDto> getRecentPosts(int limit) {
        return postRepository.findTop3ByOrderByCreatedOnDesc().stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(PostMapper:: mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email=SecurityUtils.getCurrentUser().getUsername();
       User createdBy= userRepository.findByEmail(email);
       Long userId= createdBy.getId();
      List<Post>posts= postRepository.findPostByUser(userId);
      return posts.stream().map((post)-> PostMapper.mapToPostDto(post)).collect(Collectors.toList());

    }

    @Override
    public void createPost(PostDto postDto) {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User user=userRepository.findByEmail(email);
        Post post=PostMapper.mapToPost( postDto);
        post.setCreateBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
      Post post=postRepository.findById(postId).get();
      return PostMapper.mapToPostDto(post);

    }

    @Override
    public void updatePost(PostDto postDto) {
        String email=SecurityUtils.getCurrentUser().getUsername();
        User createdBy=userRepository.findByEmail(email);
        Post post=PostMapper.mapToPost(postDto);
        post.setCreateBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post=postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts=postRepository.searchPosts(query);
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }


}
