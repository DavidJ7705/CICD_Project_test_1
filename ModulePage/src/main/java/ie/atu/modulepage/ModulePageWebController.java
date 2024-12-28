package ie.atu.modulepage;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ModulePageWebController {
    private final ModulePageService modulePageService;

    public ModulePageWebController(ModulePageService modulePageService) {
        this.modulePageService = modulePageService;
    }
    @GetMapping("/module/view/bycourse/{courseId}")
    public String ShowModulesForCourse(@Valid @PathVariable Long courseId, Model model) {
        model.addAttribute("modules", modulePageService.getModuleByCourse(courseId));
        return "modules";
    }
}
