package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;

import java.util.List;

public interface SolutionService {
    Long write(Solution solution);
    List<Solution> findByWorry(Worry worry);
    List<Solution> findByMember(String memberId);

    void acceptSolution(Long solutionId);
}
