package ie.atu.profilepage;

import ie.atu.profilepage.dto.Comments;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/profile")
@RestController
@CrossOrigin(origins = "http://localhost:8085") // Enable CORS for this controller
public class ProfilePageController {
    private final ProfilePageService profilePageService;

    public ProfilePageController(ProfilePageService profilePageService) {
        this.profilePageService = profilePageService;
    }

    @GetMapping("/info")
    public Map<String, String> getProfileInfo(){
        return profilePageService.getProfileInfo();
    }

    @GetMapping("/post")
    public Map<String, String> getUserPost(){
        return profilePageService.getUserPost();
    }
    @GetMapping("/like")
    public Map<String, String> getUserPostByLike(){
        return profilePageService.getUserPostByLike();
    }

    @PutMapping("/update")
    public String updateProfile(@Valid @RequestBody Map<String, Object> updatedDetails) {
        return profilePageService.updateProfile(updatedDetails);
    }

    @GetMapping("/courses")
    public List<Map<String, String>> getCourses() {
        return profilePageService.getAllCourses();
    }

    @GetMapping("/comments")
    public List<Comments>   getUsersComments(){
        return profilePageService.getUsersComments();

    }
}