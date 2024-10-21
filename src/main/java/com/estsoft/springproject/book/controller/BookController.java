package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.domain.BookDTO;
import com.estsoft.springproject.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //책 전체 조회 GET /books
    @GetMapping
    public String showAll(Model model) {
        List<BookDTO> list = bookService.findAll()
                .stream()
                .map(BookDTO::new)
                .toList();

        model.addAttribute("bookList", list);
        return "bookManagement";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable String id, Model model) {
        Book book = bookService.findBy(id);
        model.addAttribute("book", new BookDTO(book));
        return "bookDetail";
    }

    @PostMapping
    public String addBook(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String author) {

        bookService.saveOne(new Book(id, name, author));

        return "redirect:/books";
    }


}
