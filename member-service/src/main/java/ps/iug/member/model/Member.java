package ps.iug.member.model;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String name;
    private String email;
    private String membershipType; // STUDENT, FACULTY, PUBLIC
    private String status; // ACTIVE, SUSPENDED, EXPIRED
    private int maxLoansAllowed;
    private int currentLoans;
    private double fineAmount;

    public Member() {}

    public Member(String memberId, String name, String email,
                  String membershipType, int maxLoansAllowed) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
        this.status = "ACTIVE";
        this.maxLoansAllowed = maxLoansAllowed;
        this.currentLoans = 0;
        this.fineAmount = 0.0;
    }

    public Long getId() { return id; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getMaxLoansAllowed() { return maxLoansAllowed; }
    public void setMaxLoansAllowed(int maxLoansAllowed) { this.maxLoansAllowed = maxLoansAllowed; }
    public int getCurrentLoans() { return currentLoans; }
    public void setCurrentLoans(int currentLoans) { this.currentLoans = currentLoans; }
    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }
}
