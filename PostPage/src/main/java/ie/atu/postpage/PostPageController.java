package ie.atu.postpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostPageController {

    private PostPageService postService;
    public PostPageController (PostPageService postService)
    {
        this.postService = postService;
    }


    //This will return all the posts regardless of what module it is from.
    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAllPosts(){
        Map<String, Object> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
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
    @PostMapping("/")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.addPost(post));
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

}
