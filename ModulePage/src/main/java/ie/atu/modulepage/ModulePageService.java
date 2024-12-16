package ie.atu.modulepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModulePageService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getModulesByCourse(Long courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }

    public Module updateModule(Long id, Module updatedModule) {
        Module module = moduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Module not found with id: " + id));
        module.setName(updatedModule.getName());
        module.setDescription(updatedModule.getDescription());
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }
}
