package ie.atu.mainpage;

public class CourseDTO {
    private Long courseId;
    private String name;
    private String description;

    public CourseDTO(Long courseId, String name, String description) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
