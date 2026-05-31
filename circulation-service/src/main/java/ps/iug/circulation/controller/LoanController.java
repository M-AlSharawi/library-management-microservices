package ps.iug.circulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.iug.circulation.model.Loan;
import ps.iug.circulation.service.LoanService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoan(@PathVariable String loanId) {
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }

    @GetMapping("/member/{memberId}")
    public List<Loan> getLoansByMember(@PathVariable String memberId) {
        return loanService.getLoansByMember(memberId);
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Map<String, String> request) {
        String memberId = request.get("memberId");
        String bookId = request.get("bookId");
        return ResponseEntity.status(201).body(loanService.createLoan(memberId, bookId));
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<Loan> returnBook(@PathVariable String loanId) {
        return ResponseEntity.ok(loanService.returnBook(loanId));
    }
}
