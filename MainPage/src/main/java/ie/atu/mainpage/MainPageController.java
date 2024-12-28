package ie.atu.mainpage;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
@CrossOrigin(origins = "http://localhost:8080") // Enable CORS for this controller
public class MainPageController {

    private final MainPageService courseService;

    public MainPageController(MainPageService courseService) {
        this.courseService = courseService;
    }

    //This is basically what main page will looks like, it will be a list of courses and descriptions
    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = courseService.getAllCourses();
        return ResponseEntity.ok(response);
    }
    //Return all modules.
    @GetMapping("/getallmodules")
    public ResponseEntity<Map<String, Object>> getAllModules() {
        Map<String, Object> modules = courseService.getAllModules();
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/userCourse")
    public ResponseEntity<Map<String, Object>> getUserCourses() {
        Map<String, Object> response = courseService.getUserCourses();
        return ResponseEntity.ok(response);
    }
    //This happens when you click a specific course on the webpage. It then will redirect you to the module page, where it will be a list of modules of that specific course the user clicked.
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    @GetMapping("/getSignedInUser")
    public ResponseEntity<Map<String, String>> getSignedInUserInfo() {
        Map<String, String> userInfo = courseService.getSignedInUserInfo();
        return ResponseEntity.ok(userInfo);
    }

    // Add a new course
    @PostMapping("/addCourse")
    public String addCourse(@Valid @RequestBody Course course) {
        String response = courseService.addCourse(course.getName(), course.getDescription());
        return response;
    }

    // Update a course
    @PutMapping("/updateCourse/{id}")
    public String updateCourse(@Valid @PathVariable Long id, @Valid @RequestBody Course updatedCourse) {
        String response = courseService.updateCourse(id, updatedCourse);
        return response;
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@Valid @PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/selectedCourse")
    public Long getSelectedCourse() {
        return courseService.getSelectedCourseId();
    }

    @GetMapping("/SignUpCourses")
    public ResponseEntity<List<Map<String, String>>> SignUpCourses() {
        List<Map<String, String>> response = courseService.SignUpCourses(); // Assume it returns a List<Map>
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getProfileCourse")
    public String getProfileCourse(){
        return courseService.getProfileCourse();
    }

    @GetMapping("/getCombinedNames")
    public List<Map<String, String>> getCombinedNames() {
        List<Map<String, String>> response = courseService.getCombinedNames();
        return ResponseEntity.ok(response).getBody();
    }

    @GetMapping("/isModerator")
    public boolean isModerator(){
        return courseService.isModerator();
    }
}

