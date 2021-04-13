package se.sdaproject.dto;
import javax.validation.constraints.NotBlank;

/**
 * Topic Data Transfer Objects
 */
public class TopicDTO {

    private Long id;

    @NotBlank
    private String name;

    public TopicDTO() {
    }

    public TopicDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter and Setter methods for id,name.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
