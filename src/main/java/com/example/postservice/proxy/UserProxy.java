package com.example.postservice.proxy;

import com.example.postservice.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "user-service", url = "${USER_SERVICE_URI:http://localhost}:8080")
public interface UserProxy {

    @GetMapping("/users/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id);

    @PutMapping("/users/update-amount-of-post/{id}")
    public void updateAmountOfPost(@PathVariable Long id, @RequestParam Boolean isPost);

}
