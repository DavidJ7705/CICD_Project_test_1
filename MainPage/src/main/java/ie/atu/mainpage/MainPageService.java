package ie.atu.mainpage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MainPageService {

    private AuthClient authClient;
    private CourseRepository courseRepository;

    private ModuleClient moduleClient;

    private long selectedCourseId;
    private String selectedCourse;

    public MainPageService(CourseRepository courseRepository, AuthClient authClient, ModuleClient moduleCLient) {
        this.courseRepository = courseRepository;
        this.authClient = authClient;
        this.moduleClient = moduleCLient;
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
    // Get all modules for a specific course
    public Map<String, Object> getAllModules() {
        String modules = moduleClient.getAllModules();
        System.out.println("Fetched modules: " + modules);

        Map<String, Object> response = new HashMap<>();
        response.put("modules",modules);
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
        courseDetails.put("courseId", String.valueOf(courseId));
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
    public String addCourse(String name, String description) {

        if (!authClient.isModerator()){
            return ("Fail");
        }
        Course newCourse = new Course(name, description);
        courseRepository.save(newCourse);
        return newCourse.getName() + " is added!";
    }

    // Update an existing course
    public String updateCourse(Long id, Course updatedCourse) {
        if (!authClient.isModerator()){
            return ("Fail");
        }
        List<Course> courses = courseRepository.findAll();
        for (Course c : courses){
            if (c.getCourseId().equals(id)){
                c.setName(updatedCourse.getName());
                c.setDescription(updatedCourse.getDescription());
                courseRepository.save(c);
                return updatedCourse.getName() + " updated!";
            }
        }
        return "Error updating course!";
    }

    // Delete a course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Long getSelectedCourseId(){
        return selectedCourseId;
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

    public String getProfileCourse() {
        Course course = courseRepository.findById(authClient.getCourseIdByUsername())
            .orElseThrow(() -> new RuntimeException("Course not found for id: " + authClient.getCourseIdByUsername()));
        return course.getName();
    }

    public List<Map<String, String>> getCombinedNames() {
        List<Map<String, String>> combinedList = new ArrayList<>();

        // Fetch modules and add the "type" field
        List<Map<String, String>> modules = moduleClient.getModuleName();
        for (Map<String, String> module : modules) {
            module.put("type", "module"); // Add the "type" field as "module"
            combinedList.add(module);
        }

        // Fetch courses and add the "type" field
        List<Map<String, String>> courses = SignUpCourses();
        for (Map<String, String> course : courses) {
            course.put("type", "course"); // Add the "type" field as "course"
            combinedList.add(course);
        }

        return combinedList;
    }

    public boolean isModerator() {
        return authClient.isModerator();
    }
}

