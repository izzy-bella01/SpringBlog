package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model) {

        User user = userDao.getById(1);

        model.addAttribute("email", user.getEmail());
        model.addAttribute("postById", postDao.findById(id));


        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String postCreateForm(Model model) {
        Post newPost = new Post();
        model.addAttribute("post", newPost);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submitPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        emailService.prepareAndSend(post, "Ad Created", "You have created a new ad on SpringBlog Lister!");
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(Model model, @PathVariable long id) {

        Post editPost = postDao.findById(id);

        model.addAttribute("post", editPost);

        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String showEditPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }
}
