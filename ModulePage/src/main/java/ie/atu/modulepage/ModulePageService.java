package ie.atu.modulepage;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String signedUser = authClient.getSignedUser();
        List <Module> modules = moduleRepository.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("signedUser", signedUser);
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
        return moduleRepository.findByCourseId(courseId);
    }

    public Map<String, Object> getAllSelectedModules(){
        String signedUser = authClient.getSignedUser();
        long selectedCourseId = mainClient.getSelectedCourse();
        String selectedCourse = mainClient.getCourseName();
        List <Module> modules = moduleRepository.findByCourseId(selectedCourseId);

        Map<String, Object> response = new HashMap<>();
        response.put("Signed In User", signedUser);
        response.put("Selected Course", selectedCourse);
        response.put("Modules",modules);

        return response;
    }

    public Long getSelectedModule(){
        return selectedModuleId;
    }

    public String getModuleName(){
        return selectedModule;
    }

    // Add a new module
    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }

    // Update a module
    public Module updateModule(Long id, Module updatedModule) {
        Module module = getModuleById(id);
        module.setName(updatedModule.getName());
        module.setCourseId(updatedModule.getCourseId());
        return moduleRepository.save(module);
    }

    // Delete a module
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

}
