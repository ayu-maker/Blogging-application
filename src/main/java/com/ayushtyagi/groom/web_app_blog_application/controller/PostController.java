package com.ayushtyagi.groom.web_app_blog_application.controller;

import com.ayushtyagi.groom.web_app_blog_application.dto.PostDto;
import com.ayushtyagi.groom.web_app_blog_application.entity.Post;
import com.ayushtyagi.groom.web_app_blog_application.services.PostService;
import com.ayushtyagi.groom.web_app_blog_application.util.ROLE;
import com.ayushtyagi.groom.web_app_blog_application.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/admin/posts")
    public String posts(Model model){
        String role= SecurityUtils.getRole();
        List<PostDto> posts=null;
        if(ROLE.ROLE_ADMIN.name().equals(role)){
            posts=postService.findAllPosts();

        }
        else{
            posts=postService.findPostsByUser();
        }

        model.addAttribute("posts",posts);
        return "/admin/posts";
    }
    @GetMapping("/admin/post/newpost")
    public String newPost(Model model){
        PostDto postDto=new PostDto();
        model.addAttribute("post",postDto);
        return "/admin/newpost";
    }
    //handler method to handle post submission
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            return "admin/newpost";
        }
        postDto.setUrl(getUrl(postDto.getTittle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";

    }

    @GetMapping("/admin/posts/{postId}/edit")
    public String editPost(@PathVariable("postId")Long postId,Model model){
        PostDto postDto=postService.findPostById(postId);
        model.addAttribute("post",postDto);
        return "admin/edit_post";

    }
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId")Long postId,@Valid @ModelAttribute("post")PostDto post,BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("post",post);
            return "admin/edit_post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";

    }
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }
    //handler method to handle view post request
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl")String postUrl,Model model){
        PostDto postDto=postService.findPostByUrl(postUrl);
        model.addAttribute("post",postDto);
        return "admin/view_post";
    }

    //handler method to handle search blog posts request
    //localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam("query")String query,Model model){
        List<PostDto> posts=postService.searchPosts(query);
        System.out.println("Search query: " + query);
        System.out.println("Results found: " + posts.size());
        model.addAttribute("posts",posts);
        return "admin/posts";
    }
   private static String getUrl(String postTitle){
        //OOPS Concepts Explained in Java
        // oops-concepts-explained-in-Java
        String title=postTitle.trim().toLowerCase();
        String url=title.replaceAll("\\s+","-");
        url=url.replaceAll("[^A-Za-z0-9]","-");
        return  url;
    }


    /***** Frontend Controller ****/
    @GetMapping("/index")
        public  String index(){
            return "index";
        }

     @GetMapping("/about")
        public String about(){
            return "about";

     }

     @GetMapping("/contact")
    public String contact(){
        return "contact";
     }
     @GetMapping("/service")
    public String service(){
        return "service";
     }



}
