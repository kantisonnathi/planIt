package in.ac.bitspilani.webapp.category;

import org.springframework.data.repository.Repository;

import java.util.List;


public interface CategoryRepository extends Repository<Category, Integer> {

    /*@Query("SELECT category FROM Category category left join fetch category.listOfItems WHERE category.id =:id")
    @Transactional(readOnly = true)*/

/*
    Category findById(Integer CategoryId);
*/


    List<Category> findByUserId(Integer userId);

    Category findById(Integer categoryId);

    Category save(Category category);

    Long deleteById(Integer categoryId);
}
