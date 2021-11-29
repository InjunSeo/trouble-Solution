package jpaProject.trouble_Solution.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WorrySearch {
    private String memberId;
    private SolvedStatus solvedStatus;
}
