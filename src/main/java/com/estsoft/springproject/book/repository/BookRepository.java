package com.estsoft.springproject.book.repository;

import com.estsoft.springproject.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
