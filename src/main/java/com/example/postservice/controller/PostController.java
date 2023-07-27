package com.example.postservice.controller;

import com.example.postservice.View;
import com.example.postservice.entity.Post;
import com.example.postservice.service.PostService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody @JsonView(value = View.PostView.Post.class) @Valid Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updateUser(@PathVariable Long id, @RequestBody @JsonView(value = View.PostView.PUT.class) @Valid Post post) {
        return new ResponseEntity<>(postService.updatePost(id, post), HttpStatus.OK);
    }

    @GetMapping("/posts/getByAuthorId/{id}")
    public Boolean getPostByAuthorId(@PathVariable Long id) {
        return postService.getPostByAuthorId(id);
    }

    @GetMapping("/posts/health")
    public ResponseEntity<Void> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
