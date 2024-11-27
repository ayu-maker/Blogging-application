package com.ayushtyagi.groom.web_app_blog_application.controller;

import com.ayushtyagi.groom.web_app_blog_application.dto.CommentDto;
import com.ayushtyagi.groom.web_app_blog_application.dto.PostDto;
import com.ayushtyagi.groom.web_app_blog_application.services.CommentServices;
import com.ayushtyagi.groom.web_app_blog_application.services.PostService;
import com.ayushtyagi.groom.web_app_blog_application.util.ROLE;
import com.ayushtyagi.groom.web_app_blog_application.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class CommentController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentServices commentServices;

    //handler method to create form submit request
    @PostMapping("/user/{postUrl}/comments")
    public String createComment(@PathVariable(value ="postUrl")String postUrl, @Valid @ModelAttribute("comment") CommentDto commentDto, Model model, BindingResult result){
        PostDto postDto=postService.findPostByUrl(postUrl);

        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            model.addAttribute("comment",commentDto);
            return "blog/read_post";
        }
        commentServices.createComment(postUrl,commentDto);
        return "redirect:/blog/posts/view/"+postUrl;

    }
    @GetMapping("/admin/comments")
    public String viewComments(Model model){
        String role= SecurityUtils.getRole();
        List<CommentDto> comments=null;
        if(ROLE.ROLE_ADMIN.name().equals(role)){
            comments=commentServices.findAllComment();

        }
        else{
            comments=commentServices.findCommentsByPost();
        }

        model.addAttribute("comments",comments);
        return "/blog/commenttable";

    }
    @GetMapping("/admin/comment/{commentId}/delete")
    public String deleteComment(@PathVariable("commentId")Long commentId){
        commentServices.deleteComment(commentId);
        return "redirect:/admin/comments";
    }

}
