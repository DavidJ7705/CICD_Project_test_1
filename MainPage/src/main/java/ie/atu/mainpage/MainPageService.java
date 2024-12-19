package ie.atu.mainpage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import java.util.List;


@Service
public class MainPageService {

    private AuthClient authClient;
    private CourseRepository courseRepository;

    private long selectedCourseId;
    private String selectedCourse;

    public MainPageService(CourseRepository courseRepository, AuthClient authClient) {
        this.courseRepository = courseRepository;
        this.authClient = authClient;
    }

    // Get all courses
    public Map<String, Object> getAllCourses() {
        String signedUser = authClient.getSignedUsername();

        List<Course> courses = courseRepository.findAll();

        Map<String, Object> response = new HashMap<>();

        response.put("Signed In User", signedUser);
        response.put("Courses", courses);

        return response;
    }

    // Get a course by ID
    public Course getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found: " + id));

        selectedCourseId = id;
        selectedCourse = course.getName();
        return course;
    }



    public Map<String, String> getSignedInUserInfo() {
        String signedUsername = authClient.getSignedUsername();  // Get signed-in user's name
        String signedName = authClient.getSignedName();
        String signedEmail = authClient.getSignedEmail();
        // You might also want to call another endpoint or method to get the user's email if needed

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", signedUsername);
        userInfo.put("Name", signedName);
        userInfo.put("Email", signedEmail);

        return userInfo;
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

    public Long getSelectedCourseId(){
        return selectedCourseId;
    }

    public String getCourseName(){
        return selectedCourse;
    }

}
