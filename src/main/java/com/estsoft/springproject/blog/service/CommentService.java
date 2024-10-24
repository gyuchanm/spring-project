package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    public CommentService(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }
    public Comment saveComment(Long articleId, CommentRequestDTO requestDTO) {
        Article article = blogRepository.findById(articleId).orElseThrow(); //NoSuchElementException
        return commentRepository.save(new Comment(requestDTO.getBody(), article));
    }
    public Comment findComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElse(new Comment());
    }
    public Comment update(Long commentId, CommentRequestDTO request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        // 수정
        comment.updateCommentBody(request.getBody());
        return commentRepository.save(comment);
    }
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);   // delete from comment where id = ${commentId}
    }
}
