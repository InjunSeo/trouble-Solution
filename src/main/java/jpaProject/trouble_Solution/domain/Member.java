package jpaProject.trouble_Solution.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Setter @Getter
public class Member {
    @Id
    @Column(name = "memberId")
    private String id;

    private String password;

    @Column(name = "userName")
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Solution> acceptedSolutions = new ArrayList<>();

    private Integer accepted;

    @Enumerated(EnumType.STRING)
    private GenerationStatus generation;

    private LocalDateTime createDate;


}
