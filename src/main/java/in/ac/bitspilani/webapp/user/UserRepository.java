package in.ac.bitspilani.webapp.user;

import in.ac.bitspilani.webapp.category.Category;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {

    public User findById(Integer id);

     User findByEmail(String email);
     User save(User user);

     void delete(User user);

     /*List<User> findAllByUserComplete(boolean status);*/

}
