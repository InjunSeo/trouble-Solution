package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.GenerationStatus;
import jpaProject.trouble_Solution.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class WorryForm {
    private Long id;

    private Member member;

    @NotEmpty(message = "title은 필수입니다")
    private String title;

    @NotEmpty(message = "content는 필수입니다")
    private String content;

    private LocalDateTime createDate;

    private GenerationStatus generation;

    private List<Long> categoryId;


}
