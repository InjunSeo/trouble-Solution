package jpaProject.trouble_Solution.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
public class Solution {
    @Id  @GeneratedValue
    @Column(name = "solutionId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worryId")
    private Worry worry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String content;

    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) not null default 'READY'")
    private AcceptedStatus acceptedStatus;

    // == 연관관계 편의 메서드 ==//
    public void addWorry(Worry worry) {
        this.worry = worry;
        worry.getSolutions().add(this);
    }

    //== Constructor Method ==//
    public static Solution createSolution(Worry worry, Member member, String content) {
        Solution solution = new Solution();
        solution.setWorry(worry);
        solution.setMember(member);
        solution.setContent(content);
        solution.setCreateDate(LocalDateTime.now());
        solution.addAcceptedStatus(AcceptedStatus.READY);

        return solution;
    }

    //== business logic ==//
    public void addAcceptedStatus(AcceptedStatus acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
        if (acceptedStatus == AcceptedStatus.ACCEPTED) {
            member.getAcceptedSolutions().add(this);
        }
        else member.getAcceptedSolutions().remove(this);

    }


}
