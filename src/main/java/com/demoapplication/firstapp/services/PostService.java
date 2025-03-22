package com.demoapplication.firstapp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoapplication.firstapp.models.Post;
import com.demoapplication.firstapp.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postrepository;

    public List<Post> getAllData(){
      return postrepository.findAll();
    }

    public Optional<Post> getPost(Long id){
        return postrepository.findById(id);
    }

    public Post savePost(Post post){
      if(post.getId() == null){
        post.setTimeOfCreation(LocalDateTime.now());
      }
      return postrepository.save(post);
    }

    public void deletePost(Post post){
        postrepository.delete(post);
    }
    
}
