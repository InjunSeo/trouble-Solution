package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryImplTest {
    @Autowired MemberRepositoryImpl repository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void save() throws Exception {
        //given
        Member member = new Member();
        member.setId("seomoon");
        member.setName("Seo");
        repository.save(member);
        //when
        Member findMember = repository.findById(member.getId());

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
     }

}