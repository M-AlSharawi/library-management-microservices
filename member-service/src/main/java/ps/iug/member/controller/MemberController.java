package ps.iug.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.iug.member.model.Member;
import ps.iug.member.service.MemberService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable String memberId) {
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.addMember(member));
    }

    @PutMapping("/{memberId}/increment-loans")
    public ResponseEntity<String> incrementLoans(@PathVariable String memberId) {
        memberService.incrementLoans(memberId);
        return ResponseEntity.ok("Loan count incremented");
    }

    @PutMapping("/{memberId}/decrement-loans")
    public ResponseEntity<String> decrementLoans(@PathVariable String memberId) {
        memberService.decrementLoans(memberId);
        return ResponseEntity.ok("Loan count decremented");
    }

    @PutMapping("/{memberId}/fine")
    public ResponseEntity<String> addFine(@PathVariable String memberId,
                                           @RequestParam double amount) {
        memberService.addFine(memberId, amount);
        return ResponseEntity.ok("Fine added: " + amount);
    }
}
