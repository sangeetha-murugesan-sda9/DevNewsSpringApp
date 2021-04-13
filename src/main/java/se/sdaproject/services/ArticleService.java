package se.sdaproject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sdaproject.domain.Article;
import se.sdaproject.domain.Topic;
import se.sdaproject.dto.TopicDTO;
import se.sdaproject.exception.ResourceNotFoundException;
import se.sdaproject.domain.Comment;
import se.sdaproject.dto.ArticleDTO;
import se.sdaproject.dto.CommentDTO;
import se.sdaproject.repository.ArticleRepository;
import se.sdaproject.repository.CommentsRepository;
import se.sdaproject.repository.TopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private CommentsRepository commentsRepository;
    private TopicRepository topicsRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CommentsRepository commentsRepository,
                          TopicRepository topicsRepository) {
        this.articleRepository = articleRepository;
        this.commentsRepository = commentsRepository;
        this.topicsRepository = topicsRepository;
    }

    /**
     *  List all articles.
     */
    public List<ArticleDTO> listAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : articles) {
            ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getTitle(),
                    article.getBody(), article.getAuthorName());
            List<TopicDTO> topicDTOS = new ArrayList<>();
            for (Topic topic : article.getTopics()) {
                topicDTOS.add(getTopicDTO(topic));
            }
            articleDTO.setTopics(topicDTOS);
            articleDTOS.add(articleDTO);
        }
        return articleDTOS;
    }

    /**
     *  Get a specific article based on the provided id.
     */
    public ArticleDTO getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getTitle(), article.getBody(), article.getAuthorName());
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : article.getTopics()) {
            topicDTOS.add(getTopicDTO(topic));
        }
        articleDTO.setTopics(topicDTOS);
        return articleDTO;
    }

    /**
     *  Creates a new article
     */
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = new Article(articleDTO.getTitle(), articleDTO.getBody(), articleDTO.getAuthorName());
        Article updatedArticle = articleRepository.save(article);
        articleDTO.setId(updatedArticle.getId());
        return articleDTO;
    }

    /**
     *  Updates the given article.
     */
    public ArticleDTO updateArticle(Long id, ArticleDTO article) {
        Article updatedArticle = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setAuthorName(article.getAuthorName());
        updatedArticle.setBody(article.getBody());
        updatedArticle.setTitle(article.getTitle());
        updatedArticle.setTopics(updatedArticle.getTopics());
        articleRepository.save(updatedArticle);

        return new ArticleDTO(updatedArticle.getId(), updatedArticle.getTitle(),
                updatedArticle.getBody(), updatedArticle.getAuthorName());
    }

    /**
     *  Deletes the given article.
     */
    public ArticleDTO deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(article);
        return new ArticleDTO(article.getId(), article.getTitle(), article.getBody(), article.getAuthorName());
    }

    /**
     * Get all comments on article given by articleId.
     */
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

    /**
     * Creates a new comment on article .
     */
    public CommentDTO createComment(Long id, CommentDTO comment) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Comment newComment = new Comment(comment.getBody(), comment.getAuthorName());
        newComment.setArticle(article);
        Comment updatedComment = commentsRepository.save(newComment);
        ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getTitle(), article.getBody(), article.getAuthorName());
        return new CommentDTO(updatedComment.getId(), updatedComment.getBody(), updatedComment.getAuthorName(), articleDTO);

    }

    /**
     * Retrieves all topics associated with article given by articleId.
     */
    public List<TopicDTO> getTopicsByArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : article.getTopics()) {
            topicDTOS.add(getTopicDTO(topic));
        }
        return topicDTOS;
    }

    private TopicDTO getTopicDTO(Topic topic) {
        return new TopicDTO(topic.getId(), topic.getName());
    }

    /**
     * Associates the topic with the article given by articleId
     */
    public TopicDTO associateTopicToArticle(Long articleId, TopicDTO topicDTO) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        Topic topic = new Topic(topicDTO.getName());
        if (topicDTO.getId() != null) {
            Optional<Topic> existingTopic = topicsRepository.findById(topicDTO.getId());
            if (!existingTopic.isEmpty()) {
                topic.setId(existingTopic.get().getId());
            }
        }
        topic.setArticle(article);
        topic = topicsRepository.save(topic);
        TopicDTO updatedTopicDTO = new TopicDTO(topic.getId(), topic.getName());
        return updatedTopicDTO;
    }
}
