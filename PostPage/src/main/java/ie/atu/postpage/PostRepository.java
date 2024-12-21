package ie.atu.postpage;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByModuleId(Long moduleId);
    List<Post> findByUserId(Long userId);

    List<Post> findByUsername(String username);
}
