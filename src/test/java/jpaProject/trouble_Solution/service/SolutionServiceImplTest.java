package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.repository.SolutionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SolutionServiceImplTest {
    @PersistenceContext EntityManager em;
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    SolutionServiceImpl solutionService;

    @Test
    void writeSolution() throws Exception {
        // Let
        Member member = new Member();
        member.setId("seomoon");
        em.persist(member);

        Solution solution = new Solution();
        solution.setMember(member);
        em.persist(solution);
        // when
        Long savedId = solutionService.write(solution);
        System.out.println("savedId = " + savedId);
        List<Solution> findSolutions = solutionRepository.findByMember(member.getId());
        List<Long> solutionIds = new ArrayList<>();
        for (Solution findSolution : findSolutions) {
            Long id = findSolution.getId();
            solutionIds.add(id);
        }
        System.out.println("solutionIds = " + solutionIds);
        // then
        assertThat(solutionIds).contains(savedId);

    }

    @Test
    void findByWorry() throws Exception {
        // given
        Worry worry = new Worry();
        em.persist(worry);

        Solution solution = new Solution();
        solution.setWorry(worry);
        em.persist(solution);
        Long savedId = solutionService.write(solution);

        List<Solution> findSol = solutionService.findByWorry(worry);
        List<Long> solutions = new ArrayList<>();
        for (Solution solution1 : findSol) {
            Long id = solution1.getId();
            solutions.add(id);
        }
        assertThat(solutions).contains(savedId);
    }


}