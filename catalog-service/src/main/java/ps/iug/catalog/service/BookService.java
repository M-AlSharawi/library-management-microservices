package ps.iug.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.iug.catalog.model.Book;
import ps.iug.catalog.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void initData() {
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book("b101", "978-0-13-468599-1", "Clean Code", "Robert C. Martin", "Software Engineering", 5, "A-12"));
            bookRepository.save(new Book("b102", "978-0-13-235088-4", "The Pragmatic Programmer", "David Thomas", "Software Engineering", 3, "A-13"));
            bookRepository.save(new Book("b103", "978-0-596-51774-8", "JavaScript: The Good Parts", "Douglas Crockford", "Programming", 4, "B-05"));
            bookRepository.save(new Book("b104", "978-0-13-110362-7", "The C Programming Language", "Dennis Ritchie", "Programming", 2, "B-06"));
            bookRepository.save(new Book("b105", "978-0-201-63361-0", "Design Patterns", "Gang of Four", "Software Engineering", 3, "A-20"));
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String bookId) {
        return bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void decreaseAvailability(String bookId) {
        Book book = getBookById(bookId);
        if (book.getAvailableCopies() <= 0) throw new RuntimeException("No copies available");
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }

    public void increaseAvailability(String bookId) {
        Book book = getBookById(bookId);
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }
}
