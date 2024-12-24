package ie.atu.postpage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    // Custom methods if needed
    List<Comments>findByPostId(Long postId);
    List<Comments>findByUsername(String username);
}
