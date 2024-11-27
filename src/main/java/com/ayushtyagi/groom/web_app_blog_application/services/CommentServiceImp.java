package com.ayushtyagi.groom.web_app_blog_application.services;

import com.ayushtyagi.groom.web_app_blog_application.dto.CommentDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.Comment;
import com.ayushtyagi.groom.web_app_blog_application.entity.Post;
import com.ayushtyagi.groom.web_app_blog_application.entity.User;
import com.ayushtyagi.groom.web_app_blog_application.mapper.CommentMapper;
import com.ayushtyagi.groom.web_app_blog_application.mapper.PostMapper;
import com.ayushtyagi.groom.web_app_blog_application.repository.CommentRepository;
import com.ayushtyagi.groom.web_app_blog_application.repository.PostRepository;
import com.ayushtyagi.groom.web_app_blog_application.repository.UserRepository;
import com.ayushtyagi.groom.web_app_blog_application.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentServices {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    public CommentServiceImp(CommentRepository commentRepository,PostRepository postRepository,UserRepository userRepository){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
        this.userRepository=userRepository;
    }
    @Override
    public void createComment(String postUrl, CommentDto commentDto){
        Post post=postRepository.findByUrl(postUrl).get();
        Comment comment= CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }

    @Override
    public List<CommentDto> findAllComment() {
        List<Comment> comments=commentRepository.findAll();
        return comments.stream().map(CommentMapper:: mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User createdBy=userRepository.findByEmail(email);
        Long userId=createdBy.getId();
        List<Comment> comments=commentRepository.findCommentByPost(userId);
        return comments.stream().map((comment)-> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());

    }
}
