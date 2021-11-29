package jpaProject.trouble_Solution.service;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.repository.WorryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorryServiceImpl implements WorryService{
    private final WorryRepository worryRepository;


    @Override
    @Transactional(readOnly = false)
    public Long writeWorry(Worry worry) {
        return worryRepository.save(worry);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateWorry(Long worryId, String title, String content) {
        Worry findWorry = worryRepository.findById(worryId);
        findWorry.setTitle(title);
        findWorry.setContent(content);
    }

    @Override
    public Worry findWorry(Long worryId) {
        return worryRepository.findById(worryId);
    }

    @Override
    public List<Worry> findByMember(Member member) {
        return worryRepository.findByMember(member);
    }

    @Override
    public List<Worry> findWorries() {
        return worryRepository.findAll();
    }
}
