package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.domain.Article;


public interface ArticleRepository extends JpaRepository<Article,Long>{

}