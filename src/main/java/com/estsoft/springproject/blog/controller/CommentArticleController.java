package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springproject.blog.domain.dto.CommentResponseDTO;
import com.estsoft.springproject.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentArticleController {
    private final CommentService commentService;
    public CommentArticleController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping(name = "/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponseDTO> saveCommentByArticleId(@PathVariable Long articleId,
                                                                     @RequestBody CommentRequestDTO request) {
        Comment comment = commentService.saveComment(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponseDTO(comment));
    }
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> selectCommentById(@PathVariable("commentId") Long id) {
        Comment comment = commentService.findComment(id);
        return ResponseEntity.ok(new CommentResponseDTO(comment));
    }
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateCommentById(@PathVariable Long commentId,
                                                                @RequestBody CommentRequestDTO request) {
        Comment updated = commentService.update(commentId, request);
        return ResponseEntity.ok(new CommentResponseDTO(updated));
    }
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }
}