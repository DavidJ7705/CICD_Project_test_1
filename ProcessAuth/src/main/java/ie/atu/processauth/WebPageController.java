package ie.atu.processauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class WebPageController {

    //responsible for showing the webpage fr
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("GET Login page endpoint hit!\nDisplaying webpage");  // Add this line
        return "login";
    }

}
