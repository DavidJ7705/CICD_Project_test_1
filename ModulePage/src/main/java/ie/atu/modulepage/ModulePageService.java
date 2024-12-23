package ie.atu.modulepage;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ModulePageService {

    private final ModuleRepository moduleRepository;

    private AuthClient authClient;

    private MainClient mainClient;

    private long selectedModuleId;
    private String selectedModule;

    public ModulePageService(ModuleRepository moduleRepository, AuthClient authClient, MainClient mainClient) {
        this.moduleRepository = moduleRepository;
        this.authClient = authClient;
        this.mainClient = mainClient;
    }

    // Get all modules for a specific course
    public Map<String, Object> getAllModules() {
        List <Module> modules = moduleRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("modules",modules);
        return response;
    }

    // Get a module by ID
    public Module getModuleById(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found: " + id));

        selectedModuleId = id;
        selectedModule = module.getName();

        return module;
    }

    public List<Module> getModuleByCourse(Long courseId){
        long selectedCourseId = mainClient.getSelectedCourse();
        return moduleRepository.findByCourseId(courseId);
    }

    public Map<String, Object> getAllSelectedModules(){
        String signedUser = authClient.getSignedUsername();
        long selectedCourseId = mainClient.getSelectedCourse();
        String selectedCourse = mainClient.getCourseName();
        List <Module> modules = moduleRepository.findByCourseId(selectedCourseId);

        Map<String, Object> response = new HashMap<>();
        response.put("Signed In User", signedUser);
        response.put("Selected Course", selectedCourse);
        response.put("Modules",modules);

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
    // Get all courses
    public Map<String, Object> getAllCourses() {
        String courses = mainClient.getAllCourses();
        Map<String, Object> response = new HashMap<>();
        response.put("Courses", courses);
        return response;
    }
    public Long getSelectedModule(){
        return selectedModuleId;
    }

    public List<Map<String, String>> getModuleName(){

        return moduleRepository.findAll().stream()
                .map(module -> {
                    Map<String, String> moduleMap = new HashMap<>();
                    moduleMap.put("id", String.valueOf(module.getId()));
                    moduleMap.put("name", module.getName());
                    return moduleMap;
                })
                .collect(Collectors.toList());
    }

    // Add a new module
    public String addModule(String name, String description) {
        if (!authClient.isModerator()){
            return "Fail";
        }
        long selectedCourseId = mainClient.getSelectedCourse();
        Module newModule = new Module (name, selectedCourseId, description);
        moduleRepository.save(newModule);
        return "Success";
    }

    // Update a module
    public String updateModule(Long id, Module module) {
        if (!authClient.isModerator()){
            return "Fail";
        }

        Module updatedModule = moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found: " + id));

        updatedModule.setName(module.getName());
        updatedModule.setDescription(module.getDescription());
        updatedModule.setCourseId(updatedModule.getCourseId());
        moduleRepository.save(updatedModule);
        return "Success";
    }

    // Delete a module
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

}
