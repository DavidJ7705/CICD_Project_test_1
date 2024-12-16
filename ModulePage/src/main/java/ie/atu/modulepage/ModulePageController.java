package ie.atu.modulepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/module")
public class ModulePageController {

    @Autowired
    private ModulePageService moduleService;

    // Get all modules for a specific course
    @GetMapping("/course/{courseId}")
    public List<Module> getModulesByCourse(@PathVariable Long courseId) {
        return moduleService.getModulesByCourse(courseId);
    }

    // Create a new module
    @PostMapping("/")
    public Module addModule(@RequestBody Module module) {
        return moduleService.addModule(module);
    }

    // Update an existing module
    @PutMapping("/{id}")
    public Module updateModule(@PathVariable Long id, @RequestBody Module module) {
        return moduleService.updateModule(id, module);
    }

    // Delete a module
    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }
}
