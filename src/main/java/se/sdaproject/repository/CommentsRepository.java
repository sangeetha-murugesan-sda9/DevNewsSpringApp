package se.sdaproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.domain.Comment;
import java.util.List;

/**
 * Creating the Comments Repository.
 */
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleId(Long articleId);
    List<Comment> findByAuthorName(String authorName);

}