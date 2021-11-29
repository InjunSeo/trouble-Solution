package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;

import java.util.List;

public interface SolutionRepository {
    Long save(Solution solution);

    Solution findOne(Long solutionId);

    List<Solution> findByWorry(Worry worry);

    List<Solution> findByMember(String memberId);

}
