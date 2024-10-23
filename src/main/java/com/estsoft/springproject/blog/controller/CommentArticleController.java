package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springproject.blog.domain.dto.CommentResponseDTO;
import com.estsoft.springproject.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommentArticleController {

    private final CommentService commentService;

    public CommentArticleController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponseDTO> saveByArticleId(@PathVariable Long articleId,
                                                              @RequestBody CommentRequestDTO request) {
        Comment comment = commentService.saveComment(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponseDTO(comment));
    }
}
