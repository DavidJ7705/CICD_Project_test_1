package ie.atu.modulepage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "main-service", url = "http://localhost:8080/main")
public interface MainClient {

    @GetMapping("/selectedCourse")
    Long getSelectedCourse();

    @GetMapping("/getCourseName")
    String getCourseName();
    @GetMapping("/getall")
    String getAllCourses();
}
