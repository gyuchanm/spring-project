package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentResponseDTO {
    private Long id;
    private String body;
    private LocalDateTime createdAt;
    private ArticleResponse article;


    public CommentResponseDTO(Comment comment) {
        id = comment.getId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt();
        article = new ArticleResponse(comment.getArticle());
    }
}
