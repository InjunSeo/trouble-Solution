package jpaProject.trouble_Solution.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Categories {
    @Id @GeneratedValue
    @Column(name = "categoryId")
    private Long id;

    @Column(name = "categoryName")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CategoryWorry> categoryWorries = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Categories parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Categories> child = new ArrayList<>();

    // == 연관관계 편의 메서드 ==//
    public void addChildCategory(Categories child){
        this.child.add(child);
        child.setParent(this);
    }
}
