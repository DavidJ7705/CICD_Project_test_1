package ie.atu.mainpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.List;


@Service
public class MainPageService {

    private AuthClient authClient;
    private CourseRepository courseRepository;

    private long selectedCourse;
    public MainPageService(CourseRepository courseRepository, AuthClient authClient) {
        this.courseRepository = courseRepository;
        this.authClient = authClient;
    }

    // Get all courses
    public Map<String, Object> getAllCourses() {
        String signedUser = authClient.getSignedUser();

        List<Course> courses = courseRepository.findAll();

        Map<String, Object> response = new HashMap<>();

        response.put("signedUser", signedUser);
        response.put("courses", courses);

        return response;
    }

    // Get a course by ID
    public Course getCourseById(Long id) {
        selectedCourse = id;
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found: " + id));
    }


    // Add a new course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update an existing course
    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = getCourseById(id);
        course.setName(updatedCourse.getName());
        return courseRepository.save(course);
    }

    // Delete a course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Long getSelectedCourse(){
        return selectedCourse;
    }

}
