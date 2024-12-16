package ie.atu.modulepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/module")
public class ModulePageController {

    @Autowired
    private ModulePageService moduleService;

    // Get all modules for a given course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Module>> getModulesByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(moduleService.getModulesByCourseId(courseId));
    }

    // Get a specific module
    @GetMapping("/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.getModuleById(id));
    }

    // Add a new module
    @PostMapping("/")
    public ResponseEntity<Module> addModule(@RequestBody Module module) {
        return ResponseEntity.ok(moduleService.addModule(module));
    }

    // Update a module
    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable Long id, @RequestBody Module updatedModule) {
        return ResponseEntity.ok(moduleService.updateModule(id, updatedModule));
    }

    // Delete a module
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
