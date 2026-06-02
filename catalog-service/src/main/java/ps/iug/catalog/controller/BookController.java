package ps.iug.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.iug.catalog.model.Book;
import ps.iug.catalog.service.BookService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable String bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/{bookId}/decrease")
    public ResponseEntity<String> decrease(@PathVariable String bookId) {
        bookService.decreaseAvailability(bookId);
        return ResponseEntity.ok("Availability decreased");
    }

    @PutMapping("/{bookId}/increase")
    public ResponseEntity<String> increase(@PathVariable String bookId) {
        bookService.increaseAvailability(bookId);
        return ResponseEntity.ok("Availability increased");
    }
}
