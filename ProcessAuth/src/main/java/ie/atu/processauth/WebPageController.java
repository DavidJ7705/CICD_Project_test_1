package ie.atu.processauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/auth")
@Controller
public class WebPageController {

    //responsible for showing the webpage fr
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("GET Login page endpoint hit!\nDisplaying webpage");  // Add this line
        return "login";
    }
    @GetMapping("/signup")
    public String signupPage() {
        System.out.println("GET Signup page endpoint hit!\nDisplaying webpage");  // Add this line
        return "signup";
    }
}
