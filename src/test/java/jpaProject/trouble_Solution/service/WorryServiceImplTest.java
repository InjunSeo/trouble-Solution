package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.repository.WorryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class WorryServiceImplTest {
    @Autowired
    WorryRepository worryRepository;
    @Autowired
    WorryService worryService;

    @Test
    public void write() throws Exception {
        //given
        Worry worry = new Worry();
        worry.setTitle("Problem A");

        //when
        Long savedId = worryService.writeWorry(worry);
        Worry findWorry = worryRepository.findById(savedId);
        //then
        assertThat(worry).isEqualTo(findWorry);
     }

}