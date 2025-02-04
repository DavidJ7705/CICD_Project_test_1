package ie.atu.postpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.*;
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
    public Map<String, Object> getAllCourses() {
        String courses = mainClient.getAllCourses();
        Map<String, Object> response = new HashMap<>();
        response.put("Courses", courses);
        return response;
    }
    public Map<String, Object> getAllModules() {
        String modules = moduleClient.getAllModules();
        System.out.println("Fetched modules: " + modules);

        Map<String, Object> response = new HashMap<>();
        response.put("modules",modules);
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
    public void addCommentToPost(Long postId, Comments comment) {
        // Ensure the post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        String username = authClient.getSignedUsername();

        // Set the post in the comment
        comment.setPost(post);
        comment.setUsername(username); // Set the username dynamically

        // Save the comment
        commentsRepository.save(comment);
    }

    public List<Comments> getCommentsForPost(Long postId) {
        return commentsRepository.findByPostId(postId);
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

    public List<Comments> getCommentsByUsername(String username){
        return commentsRepository.findByUsername(username);

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

    public List<Map<String, Object>> getPostsAUserLiked(Long moduleId, String currentUsername) {
        List<Post> posts = postRepository.findByModuleId(moduleId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Post post : posts) {
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("id", post.getId());
            postMap.put("title", post.getTitle());
            postMap.put("content", post.getContent());
            postMap.put("username", post.getUsername());
            postMap.put("likes", post.getLikes().size());
            postMap.put("userLiked", post.getLikes().stream().anyMatch(like -> like.getUsername().equals(currentUsername)));

            response.add(postMap);
        }

        return response;
    }
    public List<Map<String, String>> getCombinedNames() {
        List<Map<String, String>> combinedList = new ArrayList<>();

        // Fetch modules and add the "type" field
        List<Map<String, String>> modules = moduleClient.getModuleNames();
        for (Map<String, String> module : modules) {
            module.put("type", "module"); // Add the "type" field as "module"
            combinedList.add(module);
        }

        // Fetch courses and add the "type" field
        List<Map<String, String>> courses = mainClient.SignUpCourses();
        for (Map<String, String> course : courses) {
            course.put("type", "course"); // Add the "type" field as "course"
            combinedList.add(course);
        }

        return combinedList;
    }


}
