package ps.iug.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ps.iug.catalog.model.Book;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookId(String bookId);
}
