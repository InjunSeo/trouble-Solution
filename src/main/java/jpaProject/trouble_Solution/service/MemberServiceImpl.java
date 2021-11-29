package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public String join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // id가 중복인 회원 validate
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findById(member.getId());
        if (findMember != null) {
            throw new IllegalStateException("Already existing member.");
        }
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findOne(String memberId) {
        return memberRepository.findById(memberId);
    }


}
