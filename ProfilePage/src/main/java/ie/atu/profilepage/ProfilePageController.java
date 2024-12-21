package ie.atu.profilepage;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}