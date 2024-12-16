package ie.atu.postpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostPageController {

    @Autowired
    private PostPageService postService;

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
