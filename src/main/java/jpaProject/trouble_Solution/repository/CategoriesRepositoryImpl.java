package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoriesRepositoryImpl implements CategoriesRepository {
    private final EntityManager em;

    @Override
    public void addCategory(Categories categories) {
        em.persist(categories);
    }

    @Override
    public List<Categories> findAll() {
        return em.createQuery("select c from Categories c", Categories.class)
                .getResultList();
    }

    @Override
    public Categories finById(Long id) {
        return em.find(Categories.class, id);
    }
}
