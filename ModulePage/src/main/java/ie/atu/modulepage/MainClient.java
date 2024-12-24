package ie.atu.modulepage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "main-service", url = "http://localhost:8080/main")
public interface MainClient {

    @GetMapping("/selectedCourse")
    Long getSelectedCourse();

    @GetMapping("/getCourseName")
    String getCourseName();
    @GetMapping("/getall")
    String getAllCourses();
    @GetMapping("/SignUpCourses")
    List<Map<String, String>> SignUpCourses();
}
