package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.AcceptedStatus;
import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;

    @Override
    @Transactional(readOnly = false)
    public Long write(Solution solution) {
        return solutionRepository.save(solution);
    }


    @Override
    public List<Solution> findByWorry(Worry worry) {
        return solutionRepository.findByWorry(worry);
    }

    @Override
    public List<Solution> findByMember(String memberId) {
        return solutionRepository.findByMember(memberId);
    }

    @Override
    @Transactional(readOnly = false)
    public void acceptSolution(Long solutionId) {
        Solution solution = solutionRepository.findOne(solutionId);
        solution.addAcceptedStatus(AcceptedStatus.ACCEPTED);
    }


}
