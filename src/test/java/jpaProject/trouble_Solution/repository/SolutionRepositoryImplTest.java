package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;
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
class SolutionRepositoryImplTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    SolutionRepositoryImpl solutionRepository;

    @Test
    void save() throws Exception {
        //given
        Worry worry = new Worry();
        em.persist(worry);

        Solution solution = new Solution();
        solution.setWorry(worry);
        solution.setContent("Save content");
        em.persist(solution);

        //when
        Long savedId = solutionRepository.save(solution);
        List<Solution> findSolutions = solutionRepository.findByWorry(worry);
        //then
        assertThat(findSolutions).contains(solution);
    }
    @Test
    void findByMember() throws Exception {
        //given
        Member memberA = new Member();
        memberA.setId("seomoon");
        em.persist(memberA);

        Solution solutionA = new Solution();
        solutionA.setMember(memberA);
        em.persist(solutionA);
        //when
        Long savedId = solutionRepository.save(solutionA);
        List<Solution> findSolutions = solutionRepository.findByMember(memberA.getId());

        //then
        List<Long> findIds = new ArrayList<>();
        for (Solution findSolution : findSolutions) {
            Long id = findSolution.getId();
            findIds.add(id);
        }
        assertThat(findIds).contains(savedId);
    }
}