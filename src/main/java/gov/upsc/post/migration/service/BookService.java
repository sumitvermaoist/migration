package gov.upsc.post.migration.service;
import gov.upsc.post.migration.entity.Book;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final WebClient webClient;

    // You can configure baseUrl once here
    public BookService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://openlibrary.org/") // replace with your API base url

                .defaultHeader("Accept", "application/json")
                .build();
    }

    // Fetch book details by ID
    public Mono<String> getBookById(String bookId) {
        System.out.println("The book id is : " + bookId);
        return webClient.get()
                .uri("/isbn/{isbn}/", bookId) // GET https://api.example.com/books/{id}
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(System.out::println);
    }

    // Fetch all books
    public Mono<Book[]> getAllBooks() {
        return webClient.get()
                .uri("/books")
                .retrieve()
                .bodyToMono(Book[].class);
    }
}
