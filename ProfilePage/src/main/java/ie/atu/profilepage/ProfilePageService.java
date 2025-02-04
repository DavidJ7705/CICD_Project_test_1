package ie.atu.profilepage;

import ie.atu.profilepage.dto.Comments;
import ie.atu.profilepage.dto.Post;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProfilePageService {
    private AuthClient authClient;
    private MainClient mainClient;

    private PostClient postClient;

    public ProfilePageService(AuthClient authClient, MainClient mainClient, PostClient postClient) {
        this.authClient = authClient;
        this.mainClient = mainClient;
        this.postClient = postClient;
    }
    public Map<String, String> getProfileInfo() {
        String signedUsername = authClient.getSignedUsername();  // Get signed-in user's name
        String signedName = authClient.getSignedName();
        String signedEmail = authClient.getSignedEmail();
        String signedCourse = mainClient.getProfileCourse();
        // You might also want to call another endpoint or method to get the user's email if needed

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", signedUsername);
        userInfo.put("Name", signedName);
        userInfo.put("Email", signedEmail);
        userInfo.put("Course", signedCourse);
        return userInfo;
    }

    public Map<String, String> getUserPost() {
        List<Post> posts = postClient.getPostsByUsername();
        Map<String, String> userPost = new HashMap<>();

        for (Post post : posts) {
            userPost.put(post.getTitle(), post.getContent());
        }

        return userPost;
    }

    public Map<String, String> getUserPostByLike() {
        List<Post> posts = postClient.getPostsById();
        Map<String, String> userPost = new HashMap<>();

        for (Post post : posts) {
            userPost.put(post.getTitle(), post.getContent());
        }

        return userPost;
    }

    public String updateProfile(Map<String, Object> updatedDetails) {
        // Debug logs for troubleshooting
        String username = authClient.getSignedUsername(); // Retrieve username
        System.out.println("Fetched username: " + username);

        if (username == null || username.isEmpty()) {
            throw new RuntimeException("Username is null or empty! Please check AuthClient or session.");
        }

        String name = (String) updatedDetails.get("name");
        String email = (String) updatedDetails.get("email");
        int courseId = (int) updatedDetails.get("courseId");

        System.out.println("Sending Feign request: username=" + username + ", name=" + name + ", email=" + email + ", courseId=" + courseId);

        return authClient.updateUserDetails(username, name, email, courseId);
    }



    public List<Map<String, String>> getAllCourses() {
        return mainClient.getSignUpCourses();
    }

    public List<Comments>getUsersComments()
    {
        String username = authClient.getSignedUsername()    ;
        return postClient.getCommentsByUsername(username);

    }
}
