package se.sdaproject.dto;

import se.sdaproject.domain.Topic;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
