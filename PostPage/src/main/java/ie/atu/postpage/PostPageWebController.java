package ie.atu.postpage;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class PostPageWebController {
    private final PostPageService postPageService;

    public PostPageWebController(PostPageService postPageService) {
        this.postPageService = postPageService;
    }

    @GetMapping("/post/view/module/{moduleId}")
    public String ShowPostsForModules(@PathVariable Long moduleId, Model model) {
        model.addAttribute("posts", postPageService.getPostsBySelectedCourse());
        return "posts";
    }
}
