package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BlogController {
    private final BlogService service;

    public BlogController(BlogService service) {
        this.service = service;
    }
    // RequestMapping (특정 url POST /articles)
    //@RequestMapping(method = RequestMethod.POST)  --> @PostMapping이랑 동일
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request) {
        log.info("request: {}, {}", request.getTitle(), request.getContent());
        Article article = service.saveArticle(request);
        //logging level
        //trace, debug, info, warn, error
        //log.warn("{}, {}", request.getTitle(), request.getContent());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.convert());
    }

    //Request Mapping  조회 : HTTP METHOD? GET
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAll() {
        List<Article> articleList = service.findAll();
        //List<ArticleResponse> 형태로 변환
        List<ArticleResponse> list = articleList.stream()
                .map(Article::convert)  //.map(article -> article.convert())
                .toList();
        return ResponseEntity.ok(list);
    }

    //단건 조회 API 만들기 (Request mapping) 만들기    GET/articles/1
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> fineById(@PathVariable Long id) {

        Article article = service.findBy(id);   //Exception (5xx server error) -> 4xx Status code

//      ArticleResponse response= new ArticleResponse(article.getId(), article.getTitle(), article.getContent());

        return ResponseEntity.ok(article.convert());
    }

    // DELETE /articles/{id}
    //@RequestMapping(method = RequestMethod.DELETE, value = "articles/{id}")
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    //PUT /articles/{id} 수정 API request body
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(@PathVariable Long id,
                                                      @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = service.update(id, request);
        return ResponseEntity.ok(updatedArticle.convert());
    }

    // reference : https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // reason : ""
    }
}
