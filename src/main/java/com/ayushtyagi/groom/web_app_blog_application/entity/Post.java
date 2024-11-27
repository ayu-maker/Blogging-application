package com.ayushtyagi.groom.web_app_blog_application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tittle;
    private String url;
    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String content;
    private String shortDescription;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatednOn;

    @ManyToOne
    @JoinColumn(name = "create_by",nullable = false)
    private User createBy;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private Set<Comment> comments=new HashSet<>();
}
