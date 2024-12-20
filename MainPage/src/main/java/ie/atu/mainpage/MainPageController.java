package ie.atu.mainpage;

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
    @GetMapping("/userCourse")
    public ResponseEntity<Map<String, Object>> getUserCourses() {
        Map<String, Object> response = courseService.getUserCourses();
        return ResponseEntity.ok(response);
    }
    //This happens when you click a specific course on the webpage. It then will redirect you to the module page, where it will be a list of modules of that specific course the user clicked.
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    @GetMapping("/getSignedInUser")
    public ResponseEntity<Map<String, String>> getSignedInUserInfo() {
        Map<String, String> userInfo = courseService.getSignedInUserInfo();
        return ResponseEntity.ok(userInfo);
    }

    // Add a new course
    @PostMapping("/")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.addCourse(course));
    }

    // Update a course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return ResponseEntity.ok(courseService.updateCourse(id, updatedCourse));
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/selectedCourse")
    public Long getSelectedCourse() {
        return courseService.getSelectedCourseId();
    }

    @GetMapping("/getCourseName")
    public String getCourseName(){
        return courseService.getCourseName();
    }
    @GetMapping("/SignUpCourses")
    public ResponseEntity<List<Map<String, String>>> SignUpCourses() {
        List<Map<String, String>> response = courseService.SignUpCourses(); // Assume it returns a List<Map>
        return ResponseEntity.ok(response);
    }
}

