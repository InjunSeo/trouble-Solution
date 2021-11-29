package jpaProject.trouble_Solution.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Worry {
    @Id @GeneratedValue
    @Column(name = "worryId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(name = "worryTitle")
    private String title;

    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "worry", cascade = CascadeType.ALL)
    private List<CategoryWorry> categoryWorries = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private GenerationStatus generation;

    @OneToMany(mappedBy = "worry", cascade = CascadeType.ALL)
    private List<Solution> solutions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'READY'")
    private SolvedStatus solvedStatus;

    private LocalDateTime solvedDate;

    //== Constructor Method==//
    public static Worry createWorry(Member member, String title, String content, GenerationStatus generation) {
        Worry worry = new Worry();
        worry.setMember(member);
        worry.setTitle(title);
        worry.setContent(content);
        worry.setGeneration(generation);
        worry.setCreateDate(LocalDateTime.now());
        worry.setSolvedStatus(SolvedStatus.READY);

        return worry;
    }

    //== 연관관계 Method ==//
    public void addSolvedStatus(SolvedStatus solvedStatus) {
        this.solvedStatus = solvedStatus;
        this.solvedDate = LocalDateTime.now();
    }

    //== business logic==//
    public void addCategory(Categories categories) {
        CategoryWorry categoryWorry = new CategoryWorry();
        categoryWorry.setWorry(this);
        categoryWorry.setCategory(categories);

        this.categoryWorries.add(categoryWorry);
    }


}
