package se.sdaproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.domain.Topic;
import java.util.List;

/**
 * Creating the Topic Repository interface.
 */
public interface TopicRepository extends JpaRepository<Topic,Long>{
    List<Topic> findByArticleId(Long articleId);
}