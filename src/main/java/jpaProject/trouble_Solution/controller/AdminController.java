package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.SessionConst;
import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.service.MemberService;
import jpaProject.trouble_Solution.service.SolutionService;
import jpaProject.trouble_Solution.service.WorryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final WorryService worryService;
    private final SolutionService solutionService;

    @GetMapping("/admin/memberDesc/{memberId}")
    public String myPage(@PathVariable String memberId,
                         @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) Member loginAdmin, Model model) {
        if (loginAdmin == null) {
            return "redirect:/";
        }

        Member member = memberService.findOne(memberId);
        List<Worry> worries = worryService.findByMember(member);
        List<Solution> solutions = solutionService.findByMember(memberId);
        model.addAttribute("member", member);
        model.addAttribute("worries", worries);
        model.addAttribute("solutions", solutions);

        return "members/memberDesc";
    }

    @GetMapping("/admin/profile/{memberId}")
    public String profile(@PathVariable String memberId,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        log.info("session.LgoinMember ={}", loginMember.getId());
        log.info("memberId ={}", memberId);

        if (loginMember == null) {
            return "redirect:/";
        }

        Member member = memberService.findOne(memberId);

        model.addAttribute("member", member);

        return "members/memberProfile";

    }
}
