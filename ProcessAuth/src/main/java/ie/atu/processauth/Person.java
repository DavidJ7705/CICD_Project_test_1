package ie.atu.processauth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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
}