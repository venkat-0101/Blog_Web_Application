package com.demoapplication.firstapp.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demoapplication.firstapp.models.Account;
import com.demoapplication.firstapp.models.Post;
import com.demoapplication.firstapp.services.AccountService;
import com.demoapplication.firstapp.services.PostService;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
  private PostService postService;
  @Autowired
  private AccountService accountService;
  
    @Override
    //This method runs once the application is launched by the spring boot and if there is no data in the database
    //this dummy data will be loaded in the database
    //This is called as database seeding
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAllData();
        //Getting all the data from the post table and checking the size 
        //If the size is 0 i.e. No posts, the default posts and users will be loaded into the db
        if(posts.size() == 0){
                Account account01 = new Account();
                account01.setFirstname("user01");
                account01.setEmail("user01@samplemail.com");
                account01.setPassword("12345");
                accountService.save(account01);
                Post post1 = new Post();
                post1.setTitle("first post");
                post1.setBody("Post 1 body.......");
                post1.setAccount(account01);
                postService.savePost(post1);


                //Account 02
                Account account02 = new Account();
                account02.setFirstname("user02");
                account02.setEmail("user02@samplemail.com");
                account02.setPassword("67890");
                accountService.save(account02);
                Post post2 = new Post();
                post2.setTitle("second post");
                post2.setBody("Post 2 Body......");
                post2.setAccount(account02);
                postService.savePost(post2);
        }
       
       
    }
    
}
