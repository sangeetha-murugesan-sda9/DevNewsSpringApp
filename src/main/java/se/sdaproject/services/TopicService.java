package se.sdaproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sdaproject.domain.Article;
import se.sdaproject.domain.Topic;
import se.sdaproject.dto.ArticleDTO;
import se.sdaproject.dto.TopicDTO;
import se.sdaproject.exception.ResourceNotFoundException;
import se.sdaproject.repository.TopicRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicsRepository;

    @Autowired
    public TopicService(TopicRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    /**
     * List all topics
     */
    public List<TopicDTO> listAllTopics() {
        List<Topic> topics = topicsRepository.findAll();
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            TopicDTO topicDTO = new TopicDTO(topic.getId(), topic.getName());
            topicDTOS.add(topicDTO);
        }
        return topicDTOS;
    }

    /**
     * Create a new topic.
     */
    public TopicDTO createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic(topicDTO.getName());
        topic = topicsRepository.save(topic);
        return new TopicDTO(topic.getId(), topic.getName());
    }

    /**
     * Updates the given topic
     */
    public TopicDTO updateTopic(Long id, TopicDTO topicDTO) {
        Topic topic = topicsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        topic.setName(topicDTO.getName());
        topic = topicsRepository.save(topic);
        return new TopicDTO(topic.getId(), topic.getName());
    }

    /**
     * Delete the given topic.
     */
    public TopicDTO deleteTopic(Long id) {
        Topic topic = topicsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        topicsRepository.delete(topic);
        return new TopicDTO(topic.getId(), topic.getName());

    }

    /**
     *  Get articles by Topic.
     */
    public List<ArticleDTO> getArticlesByTopic(Long id) {
        List<Topic> topics = topicsRepository.findByArticleId(id);

        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for (Topic topic : topics) {
            articleDTOS.add(getArticleDTO(topic.getArticle()));
        }
        return articleDTOS;
    }

    /**
     * Get article Data Transfer Object.
     */
    private ArticleDTO getArticleDTO(Article article) {
        return new ArticleDTO(article.getId(), article.getTitle(),
                article.getBody(), article.getAuthorName());
    }
}
