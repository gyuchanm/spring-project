package com.estsoft.springproject.blog.domain;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //생성자
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse convert() {
        return new ArticleResponse(id, title, content);
    }
    public void update(String title, String content) {
//        if (!title.isBlank()) { this.title = title; }
//        if (!content.isBlank()) { this.content = content; }
        this.title = title;
        this.content = content;
    }

}
