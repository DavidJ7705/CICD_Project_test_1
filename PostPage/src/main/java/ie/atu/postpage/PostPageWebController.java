package ie.atu.postpage;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
public class PostPageWebController {
    private final PostPageService postPageService;

    public PostPageWebController(PostPageService postPageService) {
        this.postPageService = postPageService;
    }

    @GetMapping("/post/view/module/{moduleId}")
    public String ShowPostsForModules(@Valid @PathVariable Long moduleId, Model model) {
        model.addAttribute(" posts", postPageService.getPostsByModuleId(moduleId));
        return "posts"; // Return the "posts" view name
    }



}
