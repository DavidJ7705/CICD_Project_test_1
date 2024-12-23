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
    @GetMapping("/getallmodules")
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
    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = moduleService.getAllCourses();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/selectedModule")
    public Long getSelectedModule(){
        return moduleService.getSelectedModule();
    }

    @GetMapping("/getModuleName")
    public ResponseEntity<List<Map<String, String>>> getModuleName(){
        List<Map<String, String>> response = moduleService.getModuleName();
        return ResponseEntity.ok(response);
    }

    // Add a new module
    @PostMapping("/addModule")
    public ResponseEntity<String> addModule(@RequestBody Module module) {
        String response = moduleService.addModule(module.getName(), module.getDescription());
        if ((response.startsWith("Success"))){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.status(400).body(response);
        }
    }

    // Update a module
    @PutMapping("/updateModule/{id}")
    public ResponseEntity<String> updateModule(@PathVariable Long id, @RequestBody Module module) {
        String response = moduleService.updateModule(id, module);
        if ((response.startsWith("Success"))){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.status(400).body(response);
        }
    }

    // Delete a module
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
