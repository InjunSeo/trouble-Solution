package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.GenerationStatus;
import jpaProject.trouble_Solution.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class WorryForm {
    private Long id;

    private Member member;

    private String title;

    private String content;

    private LocalDateTime createDate;

    private GenerationStatus generation;

    private List<Long> categoryId;


}
