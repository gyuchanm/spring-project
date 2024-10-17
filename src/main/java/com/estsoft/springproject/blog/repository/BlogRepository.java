package com.estsoft.springproject.blog.repository;


import com.estsoft.springproject.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
    //이렇게 하면 JpaRepository에서 제공해주는 CRUD를 다 사용할 수 있음.
}
