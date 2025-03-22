package com.demoapplication.firstapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demoapplication.firstapp.models.Post;
import com.demoapplication.firstapp.services.PostService;



@Controller
public class homeController {

    @Autowired
  private PostService postService;

    @GetMapping("/")
    public String home(Model model){
        List<Post> posts = postService.getAllData();
        model.addAttribute("posts", posts);
        return "home";
    }
}
