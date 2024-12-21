package ie.atu.profilepage.dto;

public class Comments {
    private Long id; // Comment ID

    private String comment; // Actual comment content

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    private String username; // Username of the commenter

}
