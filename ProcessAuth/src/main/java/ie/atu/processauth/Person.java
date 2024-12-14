package ie.atu.processauth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private
    String userId;

    private
    String name;

    private
    String userName;

    private
    String email;

    private
    String password;
    //1 for user, 2 for mod
    private int userType;
    //FK
    private int postId;

    private int courseId;

    private int commentId;

    public Person(String userId, String name, String userName, String email, String password, int userType, int postId, int courseId, int commentId) {
        this.userId = userId;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.postId = postId;
        this.courseId = courseId;
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}