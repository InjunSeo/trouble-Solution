package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.GenerationStatus;
import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final MemberService memberService;

    @ModelAttribute("generations")
    public GenerationStatus[] generationStatuses(){
        return GenerationStatus.values();
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setId(memberForm.getId());
        member.setPassword(memberForm.getPassword());
        member.setName(memberForm.getName());
        member.setGeneration(memberForm.getGeneration());
        member.setCreateDate(LocalDateTime.now());
        memberService.join(member);
        return "redirect:/";
    }

    /** 회원 전체 조회      */
    @GetMapping("/members")
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/login")
    public String login() {

        return "loginForm";
    }

    @PostMapping("login")
    public String login(BindingResult bindingResult) {
        return "";
    }
}
