package ie.atu.profilepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileWebController {
    @GetMapping("/view-profile") // Changed the mapping URL
    public String showProfilePage() {
        return "profile"; // This still resolves to "profile.html" in the templates folder
    }
}