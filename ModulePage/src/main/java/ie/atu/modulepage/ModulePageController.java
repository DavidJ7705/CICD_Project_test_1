package ie.atu.modulepage;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "http://localhost:8083") // Enable CORS for this controller

public class ModulePageController {

    private final ModulePageService moduleService;

    public ModulePageController(ModulePageService moduleService) {
        this.moduleService = moduleService;
    }


    //Return all modules.
    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> getAllModules() {
        Map<String, Object> modules = moduleService.getAllModules();
        return ResponseEntity.ok(modules);
    }

    //This will return list of modules when you clicked on specific course.
    @GetMapping("/byselectedCourse")
    public ResponseEntity<Map<String, Object>> getAllSelectedModules() {
        Map<String, Object> modules = moduleService.getAllSelectedModules();
        return ResponseEntity.ok(modules);
    }

    // Get a specific module
    @GetMapping("/bymodule/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.getModuleById(id));
    }

    @GetMapping("/bycourse/{courseId}")
    public ResponseEntity<List<Module>>getModuleByCourse(@PathVariable Long courseId){
        List<Module> modules = moduleService.getModuleByCourse(courseId);
        return ResponseEntity.ok(modules);
    }
    @GetMapping("/getSignedInUser")
    public ResponseEntity<Map<String, String>> getSignedInUserInfo() {
        Map<String, String> userInfo = moduleService.getSignedInUserInfo();
        return ResponseEntity.ok(userInfo);
    }
    @GetMapping("/getallcourses")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = moduleService.getAllCourses();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/selectedModule")
    public Long getSelectedModule(){
        return moduleService.getSelectedModule();
    }

    @GetMapping("/getModuleName")
    public String getModuleName(){
        return moduleService.getModuleName();
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
