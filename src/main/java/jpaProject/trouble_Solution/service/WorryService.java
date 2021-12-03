package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Categories;
import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Worry;

import java.util.List;

public interface WorryService {
    Long writeWorry(Worry worry);

    void updateWorry(Long worryId, String title, String content);

    Worry findWorry(Long worryId);

    List<Worry> findByMember(Member member);
    List<Worry> findWorries();
}
