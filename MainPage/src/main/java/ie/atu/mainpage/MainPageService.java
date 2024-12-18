package ie.atu.mainpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import java.util.List;


@Service
public class MainPageService {

    private CourseRepository courseRepository;
    public MainPageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get a course by ID
    public Course getCourseById(Long id) {
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
}
