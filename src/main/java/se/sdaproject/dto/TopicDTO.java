package se.sdaproject.dto;

import javax.validation.constraints.NotBlank;

public class TopicDTO {
    private long id;

    @NotBlank
    private String name;

    private ArticleDTO article;

    public TopicDTO(Long id, String name, ArticleDTO article) {
        this.id = id;
        this.name = name;
        this.article = article;
    }

    public TopicDTO(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }
}
