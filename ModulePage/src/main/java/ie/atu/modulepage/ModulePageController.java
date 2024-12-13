package ie.atu.modulepage;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/module")
public class ModulePageController {

    private final ModulePageService moduleService;

    public ModulePageController(ModulePageService moduleService) {
        this.moduleService = moduleService;
    }


    // Get all modules for a given course
    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> getAllModules() {
        Map<String, Object> modules = moduleService.getAllModules();
        return ResponseEntity.ok(modules);
    }

    // Get a specific module
    @GetMapping("/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.getModuleById(id));
    }

    @GetMapping("/bycourse/{courseId}")
    public ResponseEntity<List<Module>>getModuleByCourse(@PathVariable Long courseId){
        List<Module> modules = moduleService.getModuleByCourse(courseId);
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/byselectedCourse")
    public ResponseEntity<Map<String, Object>> getAllSelectedModules() {
        Map<String, Object> modules = moduleService.getAllSelectedModules();
        return ResponseEntity.ok(modules);
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
