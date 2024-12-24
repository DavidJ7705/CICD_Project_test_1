package ie.atu.profilepage;

import ie.atu.profilepage.dto.Comments;
import ie.atu.profilepage.dto.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="post-page-service", url = "http://localhost:8082/post")
public interface PostClient {
    @GetMapping("/user")
    List<Post> getPostsByUsername();
    @GetMapping("/like")
    List<Post> getPostsById();

    @GetMapping("comments/{username}")
    List<Comments>getCommentsByUsername(@PathVariable String username);

}
