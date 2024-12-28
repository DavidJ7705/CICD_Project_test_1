package ie.atu.postpage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Comment ID

    @NotNull(message = "Post reference cannot be null")
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference
    private Post post; // Reference to the Post entity

    @NotBlank(message = "Comment content is required")
    @Column(nullable = false)
    private String comment; // Actual comment content

    @NotBlank(message = "Username is required")
    @Column(nullable = false)
    private String username; // Username of the commenter

    public Comments() {}

    public Comments(Long id, Post post, String comment, String username) {
        this.id = id;
        this.post = post;
        this.comment = comment;
        this.username = username;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
