package se.sdaproject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sdaproject.exception.ResourceNotFoundException;
import se.sdaproject.domain.Comment;
import se.sdaproject.dto.ArticleDTO;
import se.sdaproject.dto.CommentDTO;
import se.sdaproject.repository.CommentsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentsRepository commentsRepository;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);


    @Autowired
    public CommentService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
    /**
     * Get comments by authorName
     */
    public List<CommentDTO> getCommentsByAuthorName(String authorName) {
        logger.info("Printing author Name", authorName);
        List<Comment> comments = commentsRepository.findByAuthorName(authorName);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            ArticleDTO articleDTO = new ArticleDTO(comment.getArticle().getId(), comment.getArticle().getTitle(),
                    comment.getArticle().getBody(), comment.getArticle().getAuthorName());
            CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getBody(),
                    comment.getAuthorName(), articleDTO);
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    /**
     * Updates the comment.
     */
    public CommentDTO updateComment(Long id, CommentDTO updateComment) {
        Comment comment = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        comment.setBody(updateComment.getBody());
        comment.setAuthorName(updateComment.getAuthorName());
        Comment updatedComment = commentsRepository.save(comment);

        ArticleDTO articleDTO = new ArticleDTO(comment.getArticle().getId(), comment.getArticle().getTitle(),
                comment.getArticle().getBody(), comment.getArticle().getAuthorName());
        return new CommentDTO(id, updatedComment.getBody(), updatedComment.getAuthorName(), articleDTO);
    }

    /**
     * Delete the comment.
     */
    public CommentDTO deleteComment(Long id) {
        Comment comment = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentsRepository.delete(comment);
        ArticleDTO articleDTO = new ArticleDTO(comment.getArticle().getId(), comment.getArticle().getTitle(),
                comment.getArticle().getBody(), comment.getArticle().getAuthorName());
        return new CommentDTO(comment.getId(), comment.getBody(), comment.getAuthorName(), articleDTO);
    }
}
