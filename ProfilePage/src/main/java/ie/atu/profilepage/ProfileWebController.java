package ie.atu.profilepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileWebController {
    @GetMapping("/profile/profile")
    public String showCoursesPage() {
        // Returns the HTML view (index.html) from templates
        return "profile";
    }



}
