package ps.iug.circulation.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loanId;
    private String memberId;
    private String bookId;
    private String bookTitle;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status; // ACTIVE, RETURNED, OVERDUE
    private double fineAmount;

    public Loan() {}

    public Loan(String loanId, String memberId, String bookId, String bookTitle) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.startDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
        this.status = "ACTIVE";
        this.fineAmount = 0.0;
    }

    public Long getId() { return id; }
    public String getLoanId() { return loanId; }
    public void setLoanId(String loanId) { this.loanId = loanId; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }
}
