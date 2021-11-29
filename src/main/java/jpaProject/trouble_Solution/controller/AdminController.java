package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.service.MemberService;
import jpaProject.trouble_Solution.service.SolutionService;
import jpaProject.trouble_Solution.service.WorryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final WorryService worryService;
    private final SolutionService solutionService;

    @GetMapping("/admin/mypage/{memberId}")
    public String myPage(@PathVariable String memberId, Model model) {
        Member member = memberService.findOne(memberId);
        List<Worry> worries = worryService.findByMember(member);
        List<Solution> solutions = solutionService.findByMember(memberId);
        model.addAttribute("member", member);
        model.addAttribute("worries", worries);
        model.addAttribute("solutions", solutions);

        return "members/myPage";
    }
}
