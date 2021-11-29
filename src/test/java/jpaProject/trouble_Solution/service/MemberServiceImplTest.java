package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.repository.MemberRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberRepositoryImpl memberRepository;
    @Autowired
    MemberServiceImpl memberService;

    @Test
    public void join() throws Exception {
        //given
        Member member = new Member();
        member.setId("seomoon");
        //when
        String savedId = memberService.join(member);

        //then
        assertThat(member).isEqualTo(memberRepository.findById(savedId));
    }
    @Test
    public void validate_Exception() throws Exception {
        //given
        Member member = new Member();
        member.setId("seomoon");

        Member member2 = new Member();
        member2.setId("seomoon");

        //when
        memberService.join(member);
        try {
            memberService.join(member2);

        } catch (IllegalStateException e) {
            return;
        }
        //then
        fail("Exception must have been occured");
     }

}