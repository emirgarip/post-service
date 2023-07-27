package com.example.postservice.model;

import java.util.Objects;


public class UserDTO {

    private Long id;
    private String username;
    private int amountOfPost;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, int amountOfPost) {
        this.id = id;
        this.username = username;
        this.amountOfPost = amountOfPost;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmountOfPost() {
        return amountOfPost;
    }

    public void setAmountOfPost(int amountOfPost) {
        this.amountOfPost = amountOfPost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return amountOfPost == userDTO.amountOfPost && id.equals(userDTO.id) && username.equals(userDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, amountOfPost);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", amountOfPost=" + amountOfPost +
                '}';
    }
}
