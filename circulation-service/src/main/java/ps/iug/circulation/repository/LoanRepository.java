package ps.iug.circulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ps.iug.circulation.model.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByLoanId(String loanId);
    List<Loan> findByMemberId(String memberId);
    List<Loan> findByStatus(String status);
}
