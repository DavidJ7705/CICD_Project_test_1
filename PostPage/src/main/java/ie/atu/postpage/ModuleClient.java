package ie.atu.postpage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name="module-service", url = "http://localhost:8081/module")
public interface ModuleClient {
    @GetMapping("/selectedModule")
    Long getSelectedModule();

    @GetMapping("/getModuleName")
    String getModuleName();
    @GetMapping("/getallmodules")
    String getAllModules();
    @GetMapping("/getModuleName")
    List<Map<String, String>> getModuleName();
}
