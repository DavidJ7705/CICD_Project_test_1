package ie.atu.modulepage;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModulePageService {

    private final ModuleRepository moduleRepository;

    public ModulePageService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    // Get all modules for a specific course
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
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
