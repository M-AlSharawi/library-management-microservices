package ps.iug.circulation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ps.iug.circulation.model.Loan;
import ps.iug.circulation.repository.LoanRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String CATALOG_URL = "http://localhost:8081/api/v1/books/";
    private final String MEMBER_URL = "http://localhost:8082/api/v1/members/";

    public Loan createLoan(String memberId, String bookId) {
        // 1. Validate member
        try {
            Map member = restTemplate.getForObject(MEMBER_URL + memberId, Map.class);
            if (!"ACTIVE".equals(member.get("status")))
                throw new RuntimeException("Member is not active");
        } catch (Exception e) {
            throw new RuntimeException("Member validation failed: " + e.getMessage());
        }

        // 2. Check book availability
        Map book;
        try {
            book = restTemplate.getForObject(CATALOG_URL + bookId, Map.class);
            int available = (int) book.get("availableCopies");
            if (available <= 0) throw new RuntimeException("No copies available");
        } catch (Exception e) {
            throw new RuntimeException("Book validation failed: " + e.getMessage());
        }

        // 3. Create loan
        String loanId = "L-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String bookTitle = (String) book.get("title");
        Loan loan = new Loan(loanId, memberId, bookId, bookTitle);

        // 4. Update availability and member loan count
        restTemplate.put(CATALOG_URL + bookId + "/decrease", null);
        restTemplate.put(MEMBER_URL + memberId + "/increment-loans", null);

        return loanRepository.save(loan);
    }

    public Loan returnBook(String loanId) {
        Loan loan = loanRepository.findByLoanId(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found: " + loanId));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus("RETURNED");

        // Calculate fine if overdue
        if (LocalDate.now().isAfter(loan.getDueDate())) {
            long daysOverdue = LocalDate.now().toEpochDay() - loan.getDueDate().toEpochDay();
            double fine = daysOverdue * 0.5; // 0.5 per day
            loan.setFineAmount(fine);
            restTemplate.put(MEMBER_URL + loan.getMemberId() + "/fine?amount=" + fine, null);
        }

        restTemplate.put(CATALOG_URL + loan.getBookId() + "/increase", null);
        restTemplate.put(MEMBER_URL + loan.getMemberId() + "/decrement-loans", null);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() { return loanRepository.findAll(); }

    public List<Loan> getLoansByMember(String memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    public Loan getLoanById(String loanId) {
        return loanRepository.findByLoanId(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found: " + loanId));
    }
}
