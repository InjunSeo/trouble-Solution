package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Member findById(String id);

    List<Member> findAll();

    List<Member> findByName(String name);
    
    Optional<Member> findByLoginId(String loginId);
}
