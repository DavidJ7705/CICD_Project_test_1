package ie.atu.postpage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostPageController {

    private final AuthClient authClient;

    private PostPageService postService;
    public PostPageController (AuthClient authClient, PostPageService postService)
    {
        this.authClient = authClient;
        this.postService = postService;
    }


    @GetMapping("/comments/{username}")
    public ResponseEntity<List<Comments>>getCommentsByUsername(@PathVariable String username){
        List<Comments>comments=postService.getCommentsByUsername(username);
        return ResponseEntity.ok(comments);
    }


    //This will return all the posts regardless of what module it is from.
    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAllPosts(){
        Map<String, Object> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = postService.getAllCourses();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getallmodules")
    public ResponseEntity<Map<String, Object>> getAllModules() {
        Map<String, Object> modules = postService.getAllModules();
        return ResponseEntity.ok(modules);
    }
    //This will return all posts under specific module, which user clicks on the module page.
    @GetMapping ("/getPostsBySelectedCourse")
    public ResponseEntity<Map<String, Object>> getPostsBySelectedCourse() {
        Map<String, Object> posts = postService.getPostsBySelectedCourse();
        return ResponseEntity.ok(posts);
    }


    // Get posts by module ID
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<Post>> getPostsByModule(@PathVariable Long moduleId) {
        return ResponseEntity.ok(postService.getPostsByModuleId(moduleId));
    }

    // Add a new post
    @PostMapping("/{moduleId}/addpost")
    public ResponseEntity<Post> addPost(@PathVariable Long moduleId,@RequestBody Post newPost) {
        // Get the username of the logged-in user
        String loggedInUsername = authClient.getSignedUsername();  // This is using your existing method to get the username

        // Fetch the userId by calling the AuthClient
        Long userId = authClient.getUserIdByUsername(loggedInUsername).getBody();  // Calling the new endpoint
         newPost.setUsername(loggedInUsername);
        newPost.setUserId(userId);  // Setting the user ID to associate with the post

        newPost.setModuleId(moduleId);  // Setting the module ID

        Post savedPost = postService.addPost(newPost);
        return ResponseEntity.ok(savedPost);
    }




    // Update a post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        return ResponseEntity.ok(postService.updatePost(id, updatedPost));
    }

    // Delete a post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    // Add a comment
    @PostMapping("/{postId}/comment")
    public ResponseEntity<String> addCommentToPost(@PathVariable Long postId, @RequestBody Comments comment) {
        postService.addCommentToPost(postId, comment);
        return ResponseEntity.ok("Comment posted!!");
    }

    // Add a like

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId){
        Likes like = postService.toggleLike(postId);
        if (like == null) {
            return ResponseEntity.ok("Like removed successfully.");
        } else {
            return ResponseEntity.ok("Post liked successfully.");
        }
    }

    //Get posts by user_id
    @GetMapping("/user")
    public ResponseEntity<List<Post>> getPostsByUsername() {
        return ResponseEntity.ok(postService.getPostsByUsername());
    }
    @GetMapping("/like")
    public ResponseEntity<List<Post>> getPostsById() {
        return ResponseEntity.ok(postService.getPostsById());
    }
    @GetMapping("/module/{moduleId}/detailed")
    public ResponseEntity<List<Map<String, Object>>> getPostsAUserLiked(@PathVariable Long moduleId) {
        String currentUsername = authClient.getSignedUsername();
        List<Map<String, Object>> posts = postService.getPostsAUserLiked(moduleId, currentUsername);
        return ResponseEntity.ok(posts);
    }

    // Fetch comments for a specific post
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comments>> getCommentsForPost(@PathVariable Long postId) {
        List<Comments> comments = postService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }
}
