package ie.atu.processauth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name="main-page-service", url = "http://localhost:8080/main")
public interface MainClient {

    @GetMapping("/SignUpCourses")
    ResponseEntity<List<Map<String, String>>> SignUpCourses();
}
