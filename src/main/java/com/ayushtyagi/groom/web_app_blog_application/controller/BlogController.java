package com.ayushtyagi.groom.web_app_blog_application.controller;

import com.ayushtyagi.groom.web_app_blog_application.dto.CommentDto;
import com.ayushtyagi.groom.web_app_blog_application.dto.PostDto;
import com.ayushtyagi.groom.web_app_blog_application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private PostService postService;
    @GetMapping("/posts")
    public String showRecentPosts(Model model) {
        List<PostDto> recentPosts = postService.getRecentPosts(3);
        System.out.println(recentPosts);
        model.addAttribute("posts", recentPosts);
        model.addAttribute("showAllButton", true); // Flag to show the "View All Posts" button
        return "userauth/authposts"; // Assuming your HTML template is named "posts.html"
    }

    @GetMapping("/blogposts")
    public String showPosts(Model model) {
        List<PostDto> recentPosts = postService.getRecentPosts(3);
        model.addAttribute("posts", recentPosts);
        model.addAttribute("showAllButton", true); // Flag to show the "View All Posts" button
        return "blog/view_posts"; // Assuming your HTML template is named "posts.html"
    }

    @GetMapping("/")
    public String viewBlogPosts(Model model){
        List<PostDto> postsResponse=postService.findAllPosts();
        model.addAttribute("postsResponse",postsResponse);
        return "blog/viewallposts";
    }
    @GetMapping("/viewall")
    public String ViewBlogPosts(Model model){
        List<PostDto> postsResponse=postService.findAllPosts();
        model.addAttribute("postsResponse",postsResponse);
        return "viewallblogs";
    }
    @GetMapping("/blog/page/search")
    public String searchPosts(@RequestParam(value="query")String query, Model model){
        System.out.println("Search query: " + query);
        List<PostDto> postResponse=postService.searchPosts(query);

        model.addAttribute("postsResponse",postResponse);
        return "blog/viewallposts";
    }

    @GetMapping("/blog/posts/view/{postUrl}")
    public String viewPost(@PathVariable("postUrl")String postUrl, Model model){
        PostDto postDto=postService.findPostByUrl(postUrl);
        CommentDto commentDto=new CommentDto();
        model.addAttribute("comment",commentDto);
        model.addAttribute("post",postDto);
        return "blog/read_post";
    }


    @GetMapping("/homepage")
    public String index(Model model){
        List<PostDto> recentPosts = postService.getRecentPosts(3);
        model.addAttribute("posts", recentPosts);
        model.addAttribute("showAllButton", true);
        return "index";
    }

    @GetMapping("/aboutUs")
    public String about(){
        return "about";
    }

    @GetMapping("/contactUs")
    public String contact(){
        return "contact";
    }
    @GetMapping("/blog/services")
    public String services(){
        return "services";
    }

    @GetMapping("/new")
    public String check(Model model){
        List<PostDto> recentPosts = postService.getRecentPosts(3);
        model.addAttribute("posts", recentPosts);
        model.addAttribute("showAllButton", true);
        return "userauth/newview";
    }


    @GetMapping("/authservice")
    public String authservice(){
        return "userauth/authservices";
    }

    @GetMapping("/authabout")
    public String authAbout(){
        return "userauth/authabout";
    }
    @GetMapping("/authcontact")
    public String authContact(){
        return "userauth/authcontact";
    }

    @GetMapping("/blog/authposts/view/{postUrl}")
    public String readPost(@PathVariable("postUrl")String postUrl, Model model){
        PostDto postDto=postService.findPostByUrl(postUrl);
        CommentDto commentDto=new CommentDto();
        model.addAttribute("comment",commentDto);
        model.addAttribute("post",postDto);
        return "userauth/authreadpost";
    }

    @GetMapping("/access-denied")
    public String access(){
        return "access-denied";
    }


}
