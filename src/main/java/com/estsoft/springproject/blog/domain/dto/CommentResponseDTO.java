package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.estsoft.springproject.util.DateFormatUtil.formatter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private Long articleId;
    private String body;
    private String createdAt;
    private ArticleResponse article;
    public CommentResponseDTO(Comment comment) {
        Article articleFromComment = comment.getArticle();
        commentId = comment.getId();
        articleId = articleFromComment.getId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt().format(formatter);
        article = new ArticleResponse(articleFromComment);
    }
}