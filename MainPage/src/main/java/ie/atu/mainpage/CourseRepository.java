package ie.atu.mainpage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Find a course by its ID
    Optional<Course> findById(Long id);
}
