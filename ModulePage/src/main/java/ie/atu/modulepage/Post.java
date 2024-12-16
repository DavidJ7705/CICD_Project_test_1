package ie.atu.modulepage;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module; // Post belongs to a specific module

    @Column(nullable = false)
    private String imageLink; // Image or resource link

    @Column(length = 1000)
    private String comments; // Text content for the post

    private int likes; // Number of likes the post has received
}
