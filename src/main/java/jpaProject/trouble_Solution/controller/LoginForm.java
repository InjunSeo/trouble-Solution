package jpaProject.trouble_Solution.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class LoginForm {
    @NotEmpty(message = "id값은 필수입니다")
    private String memberId;

    @NotEmpty(message = "Input your password")
    private String password;

}
