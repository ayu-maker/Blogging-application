package com.ayushtyagi.groom.web_app_blog_application.repository;

import com.ayushtyagi.groom.web_app_blog_application.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    // Method to fetch only the top 3 posts sorted by creation date in descending order
    List<Post> findTop3ByOrderByCreatedOnDesc();
    Optional<Post>findByUrl(String url);
    @Query("SELECT p FROM Post p WHERE " +
            "p.tittle LIKE CONCAT('%', :query, '%') OR " +
            "p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);


    @Query(value = "select * from posts p where p.create_by =:userId",nativeQuery = true)
    List<Post> findPostByUser(Long userId);


}
