package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolutionRepositoryImpl implements SolutionRepository {
    private final EntityManager em;

    @Override
    @Transactional(readOnly = false)
    public Long save(Solution solution) {
        if (solution.getId() != null) {
            em.persist(solution);
        } else {
            em.merge(solution);
        }
        return solution.getId();
    }

    @Override
    public Solution findOne(Long solutionId) {
        return em.find(Solution.class, solutionId);
    }

    @Override
    public List<Solution> findByWorry(Worry worry) {
        Long worryId = worry.getId();
        return (List<Solution>) em.createQuery("select s from Solution s where s.worry.id = :worryId")
                .setParameter("worryId", worryId)
                .getResultList();
    }

    @Override
    public List<Solution> findByMember(String memberId) {
        return em.createQuery("select s from Solution s where s.member.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
