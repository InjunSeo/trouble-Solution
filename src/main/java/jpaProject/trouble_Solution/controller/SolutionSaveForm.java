package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.Member;
import jpaProject.trouble_Solution.domain.Worry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class SolutionSaveForm {

    private Long id;

    private Worry worry;

    private Member member;

    @NotEmpty(message = "content는 필수입니다")
    private String content;
}
