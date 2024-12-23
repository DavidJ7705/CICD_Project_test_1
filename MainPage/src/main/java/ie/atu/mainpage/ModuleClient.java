package ie.atu.mainpage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name="module-service", url = "http://localhost:8081/module")
public interface ModuleClient {
    @GetMapping("/getModuleName")
    List<Map<String, String>> getModuleName();
}
