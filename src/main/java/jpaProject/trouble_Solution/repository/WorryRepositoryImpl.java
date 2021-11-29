package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.domain.WorrySearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorryRepositoryImpl implements WorryRepository {

    private final EntityManager em;

    @Override
    public Long save(Worry worry) {
        if (worry.getId() == null) {
            em.persist(worry);
        }else{
            em.merge(worry);
        }
        return worry.getId();
    }

    @Override
    public Worry findById(Long id) {
        return em.find(Worry.class, id);
    }

    @Override
    public List<Worry> findByMember(Member member) {
        String memberId = member.getId();
        List<Worry> worries = em.createQuery("select w from Worry w where w.member.id = :memberId", Worry.class)
                .setParameter("memberId", memberId)
                .getResultList();
        return worries;
    }

    @Override
    public List<Worry> findAll() {
        List<Worry> resultList = em.createQuery("select w from Worry w", Worry.class)
                .getResultList();
        return resultList;
    }

    /**
     * 검색: memberId, solvedStatus 검색 시, 해당 조건 가진 worry 목록 가져온다.
     */
    @Override
    public List<Worry> Search(WorrySearch worrySearch) {
        String jpql = "select w from Worry w join w.member m";
        boolean isFirstCondition = true;

        // solved status
        if (worrySearch.getSolvedStatus() != null) {
            if (isFirstCondition) {
                jpql += " where ";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " w.solvedStatus = :status";
        }
        // memberId
        if (StringUtils.hasText(worrySearch.getMemberId())) {
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else {
                jpql += " and";
            }
            jpql += " m.memberId like :memberId";
        }

        TypedQuery<Worry> query = em.createQuery(jpql, Worry.class)
                .setMaxResults(1000);
        if (worrySearch.getSolvedStatus() != null) {
            query = query.setParameter("status", worrySearch.getSolvedStatus());
        }
        if (StringUtils.hasText(worrySearch.getMemberId())) {
            query = query.setParameter("memberId", worrySearch.getMemberId());
        }
        return query.getResultList();
    }
}
