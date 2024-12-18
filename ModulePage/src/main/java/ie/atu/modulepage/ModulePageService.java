package ie.atu.modulepage;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModulePageService {

    private final ModuleRepository moduleRepository;

    private AuthClient authClient;

    public ModulePageService(ModuleRepository moduleRepository, AuthClient authClient) {
        this.moduleRepository = moduleRepository;
        this.authClient = authClient;
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
        return moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found: " + id));
    }

    public List<Module> getModuleByCourse(Long courseId){
        return moduleRepository.findByCourseId(courseId);
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
