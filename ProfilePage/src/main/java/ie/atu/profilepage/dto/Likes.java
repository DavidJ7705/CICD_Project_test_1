package ie.atu.profilepage.dto;

public class Likes {
    private Long id; // Like ID
    private String username; // Username of the user who liked the post

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
