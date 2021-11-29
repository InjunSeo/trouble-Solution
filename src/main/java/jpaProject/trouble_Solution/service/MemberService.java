package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Member;

import java.util.List;

public interface MemberService {
    String join(Member member);

    List<Member> findMembers();

    Member findOne(String memberId);

}
