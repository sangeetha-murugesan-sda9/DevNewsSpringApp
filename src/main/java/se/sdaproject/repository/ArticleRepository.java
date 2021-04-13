package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.domain.Article;
/**
 * Creating the Article Repository Interface.
 */
public interface ArticleRepository extends JpaRepository<Article,Long>{

}