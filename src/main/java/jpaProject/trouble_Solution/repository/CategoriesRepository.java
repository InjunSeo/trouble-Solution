package jpaProject.trouble_Solution.repository;

import jpaProject.trouble_Solution.domain.Categories;

import java.util.List;

public interface CategoriesRepository {
    void addCategory(Categories categories);

    List<Categories> findAll();

    Categories finById(Long id);

}
