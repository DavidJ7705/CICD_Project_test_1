package ie.atu.postpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostPageService {

    private PostRepository postRepository;
    private CommentsRepository commentsRepository;
    private LikesRepository likesRepository;

    private AuthClient authClient;
    private MainClient mainClient;

    private ModuleClient moduleClient;

    public PostPageService (PostRepository postRepository, CommentsRepository commentsRepository, LikesRepository likesRepository, AuthClient authClient, MainClient mainClient, ModuleClient moduleClient){
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
        this.likesRepository = likesRepository;
        this.authClient = authClient;
        this.mainClient = mainClient;
        this.moduleClient = moduleClient;
    }

    public Map<String, Object> getAllPosts(){
        String signedUser = authClient.getSignedName();
        String signedEmail = authClient.getSignedEmail();

        List <Post> posts = postRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("Signed In User", signedUser);
        response.put("Signed In Email", signedEmail);
        response.put("Posts", posts);

        return response;
    }

    public Map<String, Object> getPostsBySelectedCourse(){
        String signedUser = authClient.getSignedUsername();
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

    // Add a comment to a post
    public Comments addCommentToPost(Long postId, Comments comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found: " + postId));

        comment.setPost(post);
        comment.setUsername(authClient.getSignedUsername());
        return commentsRepository.save(comment);
    }

    @Transactional
    public Likes toggleLike(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found: " + postId));

        String signedUser = authClient.getSignedUsername();
        if (likesRepository.existsByPostIdAndUsername(postId, signedUser)) {
            // If the like exists, remove it (dislike)
            likesRepository.deleteByPostIdAndUsername(postId, signedUser);
            return null; // Return null to indicate the like was removed
        } else {
            // If the like doesn't exist, add it
            Likes like = new Likes();
            like.setPost(post);
            like.setUsername(signedUser);
            return likesRepository.save(like);
        }
    }

    public List<Post> getPostsByUsername() {
        String username = authClient.getSignedUsername();
        List <Post> userPosts = postRepository.findByUsername(username);
        return userPosts;
    }
    public List<Post> getPostsById(){
        String username = authClient.getSignedUsername();
        List<Likes> likes = likesRepository.findByUsername(username);

        List<Long> postIds = likes.stream()
                .map(like -> like.getPost().getId()) // Access the Post entity's ID
                .collect(Collectors.toList());
        List <Post> userPostById = postRepository.findByIdIn(postIds);
        return userPostById;
    }

}
