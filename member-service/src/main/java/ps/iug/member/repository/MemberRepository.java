package ps.iug.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ps.iug.member.model.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
}
