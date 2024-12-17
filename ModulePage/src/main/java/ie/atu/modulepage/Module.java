package ie.atu.modulepage;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Module{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long courseId; // Links to Course ID

    public Module(Long id, String name, Long courseId) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
    }

}
