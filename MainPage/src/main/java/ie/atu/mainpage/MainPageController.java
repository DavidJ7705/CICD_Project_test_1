package ie.atu.mainpage;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainPageController {

    private final MainPageService courseService;

    public MainPageController(MainPageService courseService) {
        this.courseService = courseService;
    }

    // Get all courses
    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = courseService.getAllCourses();
        return ResponseEntity.ok(response);
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
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
}

