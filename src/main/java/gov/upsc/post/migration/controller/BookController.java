package gov.upsc.post.migration.controller;

import gov.upsc.post.migration.entity.Book;
import gov.upsc.post.migration.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/mybooks/{id}")
    public Mono<String> getBook(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/mybooks")
    public Mono<Book[]> getAllBooks() {
        return bookService.getAllBooks();
    }
}
