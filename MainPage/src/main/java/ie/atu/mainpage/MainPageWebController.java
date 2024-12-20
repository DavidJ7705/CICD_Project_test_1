package ie.atu.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageWebController {
    @GetMapping("/main/viewall")
    public String showCoursesPage() {
        // Returns the HTML view (index.html) from templates
        return "index";
    }



}
