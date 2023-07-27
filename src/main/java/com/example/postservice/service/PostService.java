package com.example.postservice.service;

import com.example.postservice.entity.Post;
import com.example.postservice.exception.NotFoundException;
import com.example.postservice.model.UserDTO;
import com.example.postservice.proxy.UserProxy;
import com.example.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProxy userProxy;

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    public Post save(Post post) {
        UserDTO userDTO = userProxy.getUser(post.getAuthorId()).orElseThrow(() -> new RuntimeException("User not found"));
        Post savedPost = postRepository.save(post);
//        kafkaTemplate.send("post-topics", "true", Long.toString(post.getAuthorId()));
        userProxy.updateAmountOfPost(post.getAuthorId(), true);
        return savedPost;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deletePost(Long id) {
        Post deletePost = getPost(id);
        postRepository.delete(deletePost);
//        kafkaTemplate.send("post-topics", "false", Long.toString(deletePost.getAuthorId()));
        userProxy.updateAmountOfPost(deletePost.getAuthorId(), false);
    }

    public Post updatePost(Long id, Post post) {
        Post updatePost = getPost(id);
        updatePost.setText(post.getText());
        updatePost.setPostedAt(new Date());
        return postRepository.save(updatePost);
    }

    public Boolean getPostByAuthorId(Long id) {
        if(!postRepository.findAllByAuthorId(id).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
