package se.sdaproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sdaproject.domain.Article;
import se.sdaproject.exception.ResourceNotFoundException;
import se.sdaproject.domain.Comment;
import se.sdaproject.dto.ArticleDTO;
import se.sdaproject.dto.CommentDTO;
import se.sdaproject.exception.NotFoundException;
import se.sdaproject.repository.ArticleRepository;
import se.sdaproject.repository.CommentsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private CommentsRepository commentsRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CommentsRepository commentsRepository) {
        this.articleRepository = articleRepository;
        this.commentsRepository = commentsRepository;
    }

    public List<ArticleDTO> listAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : articles) {
            ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getTitle(),
                    article.getBody(), article.getAuthorName());
            articleDTOS.add(articleDTO);
        }
        return articleDTOS;
    }

    public ArticleDTO getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (article != null) {
            return new ArticleDTO(article.getId(), article.getTitle(), article.getBody(), article.getAuthorName());
        } else {
            throw new NotFoundException("Article does not exists");
        }
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = new Article(articleDTO.getTitle(), articleDTO.getBody(), articleDTO.getAuthorName());
        Article updatedArticle = articleRepository.save(article);
        articleDTO.setId(updatedArticle.getId());
        return articleDTO;
    }

    public ArticleDTO updateArticle(Long id, ArticleDTO article) {
        Article updatedArticle = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setAuthorName(article.getAuthorName());
        updatedArticle.setBody(article.getBody());
        updatedArticle.setTitle(article.getTitle());
        articleRepository.save(updatedArticle);

        return new ArticleDTO(updatedArticle.getId(), updatedArticle.getTitle(),
                updatedArticle.getBody(), updatedArticle.getAuthorName());
    }

    public ArticleDTO deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(article);
        return new ArticleDTO(article.getId(), article.getTitle(), article.getBody(), article.getAuthorName());
    }

    public List<CommentDTO> getComments(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getTitle(), article.getBody(), article.getAuthorName());
        List<Comment> comments = commentsRepository.findByArticleId(articleId);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getBody(), comment.getAuthorName(), articleDTO);
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    public CommentDTO createComment(Long id, CommentDTO comment) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Comment newComment = new Comment(comment.getBody(), comment.getAuthorName());
        newComment.setArticle(article);
        Comment updatedComment = commentsRepository.save(newComment);
        ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getTitle(), article.getBody(), article.getAuthorName());
        return new CommentDTO(updatedComment.getId(), updatedComment.getBody(), updatedComment.getAuthorName(), articleDTO);

    }
}
