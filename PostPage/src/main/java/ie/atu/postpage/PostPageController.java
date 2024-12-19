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

    @GetMapping ("/getall")
    public ResponseEntity<Map<String, Object>> getAllPosts() {
        Map<String, Object> posts = postService.getAllPosts();
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
}
