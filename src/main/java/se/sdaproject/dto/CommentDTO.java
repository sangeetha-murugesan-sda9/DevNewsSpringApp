package se.sdaproject.dto;
import javax.validation.constraints.NotBlank;

public class CommentDTO {
    private long id;

    @NotBlank
    private String body;

    @NotBlank
    private String authorName;

    private ArticleDTO article;

    public CommentDTO(Long id, String body, String authorName, ArticleDTO article) {
        this.id = id;
        this.body = body;
        this.authorName = authorName;
        this.article = article;
    }

    public CommentDTO(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }
}
