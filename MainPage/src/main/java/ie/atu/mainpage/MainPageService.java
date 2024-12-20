package ie.atu.mainpage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.stream.Collectors;


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

    public Map<String, Object> getUserCourses() {
        // Get the username of the logged-in user
        String signedUsername = authClient.getSignedUsername();

        // Get the courseId associated with the signed-in username
        Long courseId = authClient.getCourseIdByUsername(); // This gives us the courseId

        // Now, use the courseId to find the course from the course table (main-db)
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found for id: " + courseId));

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("userName", signedUsername);

        // Extract course name and description and return them separately
        Map<String, String> courseDetails = new HashMap<>();
        courseDetails.put("name", course.getName()); // Assuming 'getName()' is the method for course name
        courseDetails.put("description", course.getDescription()); // Assuming 'getDescription()' is the method for course description

        // Add the course details to the response
        response.put("courseDetails", courseDetails);

        return response;
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

    public List<Map<String, String>> SignUpCourses() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    Map<String, String> courseMap = new HashMap<>();
                    courseMap.put("id", String.valueOf(course.getCourseId()));
                    courseMap.put("name", course.getName());
                    return courseMap;
                })
                .collect(Collectors.toList());
    }

}
