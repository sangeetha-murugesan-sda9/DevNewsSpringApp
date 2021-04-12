package se.sdaproject.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.dto.CommentDTO;
import se.sdaproject.services.CommentService;

import java.util.List;

@RequestMapping("/comments")
@RestController
public class CommentsController {
    CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getCommentsByAuthorName(@RequestParam String authorName) {
        List<CommentDTO> commentDTOList = commentService.getCommentsByAuthorName(authorName);
        return ResponseEntity.ok().body(commentDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO updateComment) {
        CommentDTO updatedComment = commentService.updateComment(id, updateComment);
        return ResponseEntity.ok().body(updateComment);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long id) {
        CommentDTO deletedComment = commentService.deleteComment(id);
        return ResponseEntity.ok().body(deletedComment);
    }


}




