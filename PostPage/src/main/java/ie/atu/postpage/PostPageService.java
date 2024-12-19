package ie.atu.postpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostPageService {

    private PostRepository postRepository;

    private AuthClient authClient;
    private MainClient mainClient;

    private ModuleClient moduleClient;

    public PostPageService (PostRepository postRepository, AuthClient authClient, MainClient mainClient, ModuleClient moduleClient){
        this.postRepository = postRepository;
        this.authClient = authClient;
        this.mainClient = mainClient;
        this.moduleClient = moduleClient;
    }

    public Map<String, Object> getAllPosts(){
        String signedUser = authClient.getSignedUser();
        List <Post> posts = postRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("Signed In User", signedUser);
        response.put("Posts", posts);

        return response;
    }

    public Map<String, Object> getPostsBySelectedCourse(){
        String signedUser = authClient.getSignedUser();
        String selectedCourse = mainClient.getCourseName();
        String selectedModule = moduleClient.getModuleName();
        long selectedModuleId = moduleClient.getSelectedModule();
        List <Post> posts = postRepository.findByModuleId(selectedModuleId);

        Map<String, Object> response = new HashMap<>();
        response.put("Signed In User", signedUser);
        response.put("Selected Course", selectedCourse);
        response.put("Selected Module", selectedModule);
        response.put("Posts", posts);

        return response;
    }


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
