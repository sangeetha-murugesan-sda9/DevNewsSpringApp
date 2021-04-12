package se.sdaproject.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.dto.ArticleDTO;
import se.sdaproject.dto.TopicDTO;
import se.sdaproject.services.TopicService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/topics")
@RestController
public class TopicController {

    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<TopicDTO> listAllTopics() {
        List<TopicDTO> topicDTOS = topicService.listAllTopics();
        return topicDTOS;
    }

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody @Valid TopicDTO topic) {
        TopicDTO topicDTO = topicService.createTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicDTO topic) {
        TopicDTO updatedTopic = topicService.updateTopic(id, topic);
        return ResponseEntity.ok(updatedTopic);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicDTO> deleteTopic(@PathVariable Long id) {
        TopicDTO topicDTO = topicService.deleteTopic(id);
        return ResponseEntity.ok().body(topicDTO);
    }

    @GetMapping("/{id}/articles")
    public ResponseEntity<List<ArticleDTO>> getArticlesByTopic(@PathVariable Long id) {
        List<ArticleDTO> articles = topicService.getArticlesByTopic(id);
        return ResponseEntity.ok(articles);
    }
}