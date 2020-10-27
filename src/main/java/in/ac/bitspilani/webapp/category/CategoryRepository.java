package in.ac.bitspilani.webapp.category;

import in.ac.bitspilani.webapp.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends Repository<Category, Integer> {

    @Query("SELECT category FROM Category category left join fetch category.listOfItems WHERE category.id =:id")
    @Transactional(readOnly = true)
    Category findById(@Param("id") Integer id);

    List<Category> findCategoriesByUser(@Param("user") User user);
}
