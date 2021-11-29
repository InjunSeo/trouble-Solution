package jpaProject.trouble_Solution.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class CategoryWorry {
    @Id @GeneratedValue
    @Column(name = "categoryWorryId")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worryId")
    private Worry worry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Categories category;

}
