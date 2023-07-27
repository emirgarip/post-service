package com.example.postservice.entity;

import com.example.postservice.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @JsonView(value = {View.PostView.Post.class})
    private Long authorId;

    @Column
    @JsonView(value = {View.PostView.Post.class, View.PostView.PUT.class})
    @NotBlank
    private String text;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date postedAt = new Date();

    @Column
    @JsonView(value = {View.PostView.Post.class, View.PostView.PUT.class})
    @NotBlank
    private String topic;

    public Post() {
    }

    public Post(Long id, Long authorId, String text, Date postedAt) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.postedAt = postedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) && authorId.equals(post.authorId) && text.equals(post.text) && postedAt.equals(post.postedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, text, postedAt);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + authorId +
                ", text='" + text + '\'' +
                ", postedAt=" + postedAt +
                '}';
    }
}
