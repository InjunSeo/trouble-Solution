package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.GenerationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "id값은 필수입니다")
    private String id;

    @NotEmpty(message = "Input your password")
    private String password;

    private String name;

    @NotNull(message = "세대 선택은 필수입니다.")
    private GenerationStatus generation;
}
