package ie.atu.modulepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModulePageService {

    @Autowired
    private ModuleRepository moduleRepository;

    // Get all modules for a specific course
    public List<Module> getModulesByCourseId(Long courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    // Get a module by ID
    public Module getModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found: " + id));
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
