package in.ac.bitspilani.webapp.category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends Repository<Category, Integer> {

    @Query("SELECT category FROM Category category left join fetch category.listOfItems WHERE category.id =:id")
    @Transactional(readOnly = true)
    Category findById(@Param("id") Integer id);
}
