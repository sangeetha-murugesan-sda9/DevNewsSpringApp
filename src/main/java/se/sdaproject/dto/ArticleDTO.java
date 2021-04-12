package se.sdaproject.dto;
import javax.validation.constraints.NotBlank;

public class ArticleDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @NotBlank
    private String authorName;

    public ArticleDTO(Long id, String title, String body, String authorName){
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorName = authorName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
