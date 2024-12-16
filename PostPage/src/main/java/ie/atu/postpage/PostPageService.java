package ie.atu.postpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostPageService {

    @Autowired
    private PostRepository postRepository;

    // Get posts by module ID
    public List<Post> getPostsByModuleId(Long moduleId) {
        return postRepository.findByModuleId(moduleId);
    }

    // Add a new post
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    // Update a post
    public Post updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        return postRepository.save(post);
    }

    // Delete a post
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
