package com.ayushtyagi.groom.web_app_blog_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSpringSecurity {
    private UserDetailsService userDetailsService;

    public WebSpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(authorize ->
                        authorize
                                 // Allow access to the login page

                                .requestMatchers(new AntPathRequestMatcher("/blogposts")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/viewall")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/blog/page/search")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/blog/posts/view/{postUrl}")).permitAll()

                                .requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()

                                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()


                                .requestMatchers(new AntPathRequestMatcher("/jscript/**","/unauth/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                                .requestMatchers( "/new","/posts", "/authservice","/admin/**","/admin/comments","admin/post/newpost").authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/**"))
                                .hasAnyRole("ADMIN","GUEST")
                                 // Require authentication for all other requests
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/new",true) // Redirect after successful login
                        .loginProcessingUrl("/login") // URL to process login
                        .permitAll() // Allow everyone to access the login page
                ).logout(logout->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                )
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied"));



        return http.build();
    }
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}
