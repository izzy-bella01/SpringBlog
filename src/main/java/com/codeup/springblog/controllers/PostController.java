package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.services.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {

//        Post newPost = new Post("title1", "body1");
//        Post newPost2 = new Post("title2", "body2");
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(newPost);
//        postList.add(newPost2);
//
//        model.addAttribute("postArr", postList);

        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model) {
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
    public String submitPost(@ModelAttribute Post title, @ModelAttribute Post body, Model model) {

        model.addAttribute("title", title);
        model.addAttribute("body", body);
        postDao.save(title);
        postDao.save(body);

        return "redirect:/posts";
    }
}
