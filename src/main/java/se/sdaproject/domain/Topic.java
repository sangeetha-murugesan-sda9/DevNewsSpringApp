package se.sdaproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Table(name = "topics")
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id", nullable = false)
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public Topic() {
    }

    public Topic(String name) {
        this.name = name;
    }

    /**
     * Getter and Setter methods for id,article,name.
     */
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
}
