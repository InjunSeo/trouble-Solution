package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Worry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class WorryRepositoryImplTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    WorryRepository worryRepository;

    @Test
    void write() {
        //
        Worry worry = new Worry();
        worry.setTitle("worryA");
        Long savedId = worryRepository.save(worry);
        //
        Worry findWorry = worryRepository.findById(savedId);
        //
        assertThat(findWorry).isEqualTo(worry);
    }

    @Test
    void updateWorry() {
        Worry worry = new Worry();
        worry.setTitle("worryA");
        Long savedId = worryRepository.save(worry);
        //
        Worry findWorry = worryRepository.findById(savedId);
        Member member = new Member();
        member.setId("seomoon");
        em.persist(member);
        findWorry.setMember(member);

        Long updatedId = worryRepository.save(findWorry);
        Worry findWorry2 = worryRepository.findById(updatedId);
        assertThat(findWorry2.getMember()).isEqualTo(member);
    }


    @Test
    void findByMember() {
        Member member = new Member();
        member.setId("seomoon");
        em.persist(member);

        Worry worry = new Worry();
        worry.setTitle("worryA");
        worry.setMember(member);

        Long savedId = worryRepository.save(worry);
        Worry findWorry = worryRepository.findById(savedId);
        List<Worry> results = worryRepository.findByMember(member);
        assertThat(results).contains(findWorry);
    }


}