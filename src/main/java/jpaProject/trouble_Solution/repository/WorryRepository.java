package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.domain.WorrySearch;

import java.util.List;

public interface WorryRepository {
    Long save(Worry worry);

    Worry findById(Long id);

    List<Worry> findByMember(Member member);

    List<Worry> findAll();

    List<Worry> Search(WorrySearch worrySearch);
}
