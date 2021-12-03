package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.GenerationStatus;
import jpaProject.trouble_Solution.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class WorrySaveForm {
    private Long id;

    private Member member;

    @NotEmpty(message = "title은 필수입니다")
    private String title;

    @NotEmpty(message = "content는 필수입니다")
    private String content;

    private LocalDateTime createDate;
    @NotNull(message = "세대 선택은 필수입니다.")
    private GenerationStatus generation;

    @NotEmpty(message = "카테고리를 선택해주세요.")
    private List<Long> categoryId;


}
