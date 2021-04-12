package se.sdaproject.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.dto.ArticleDTO;
import se.sdaproject.dto.CommentDTO;
import se.sdaproject.services.ArticleService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleDTO> listAllArticle() {
        List<ArticleDTO> articleDTO = articleService.listAllArticles();
        return articleDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {
        ArticleDTO article = articleService.getArticle(id);
        return ResponseEntity.ok(article);
    }


    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody @Valid ArticleDTO article) {
        ArticleDTO articleDTO = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody @Valid ArticleDTO article) {
        ArticleDTO updatedArticle = articleService.updateArticle(id, article);
        return ResponseEntity.ok(updatedArticle);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleDTO> deleteArticle(@PathVariable Long id) {
        ArticleDTO articleDTO = articleService.deleteArticle(id);
        return ResponseEntity.ok().body(articleDTO);
    }

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long articleId) {
        List<CommentDTO> comments = articleService.getComments(articleId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long articleId, @RequestBody @Valid CommentDTO comment){
        CommentDTO commentDTO = articleService.createComment(articleId, comment);
        return ResponseEntity.ok().body(commentDTO);
    }
}