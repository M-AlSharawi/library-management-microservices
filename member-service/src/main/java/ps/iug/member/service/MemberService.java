package ps.iug.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.iug.member.model.Member;
import ps.iug.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @PostConstruct
    public void initData() {
        if (memberRepository.count() == 0) {
            memberRepository.save(new Member("m201", "Ahmed Al-Masri", "ahmed@lib.ps", "STUDENT", 5));
            memberRepository.save(new Member("m202", "Sara Hassan", "sara@lib.ps", "FACULTY", 10));
            memberRepository.save(new Member("m203", "Omar Khalil", "omar@lib.ps", "STUDENT", 5));
            memberRepository.save(new Member("m204", "Lina Nasser", "lina@lib.ps", "PUBLIC", 3));
        }
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found: " + memberId));
    }

    public Member addMember(Member member) {
        member.setStatus("ACTIVE");
        member.setCurrentLoans(0);
        member.setFineAmount(0.0);
        return memberRepository.save(member);
    }

    public void incrementLoans(String memberId) {
        Member m = getMemberById(memberId);
        if (m.getCurrentLoans() >= m.getMaxLoansAllowed())
            throw new RuntimeException("Loan limit reached for member: " + memberId);
        m.setCurrentLoans(m.getCurrentLoans() + 1);
        memberRepository.save(m);
    }

    public void decrementLoans(String memberId) {
        Member m = getMemberById(memberId);
        if (m.getCurrentLoans() > 0)
            m.setCurrentLoans(m.getCurrentLoans() - 1);
        memberRepository.save(m);
    }

    public void addFine(String memberId, double amount) {
        Member m = getMemberById(memberId);
        m.setFineAmount(m.getFineAmount() + amount);
        memberRepository.save(m);
    }
}
