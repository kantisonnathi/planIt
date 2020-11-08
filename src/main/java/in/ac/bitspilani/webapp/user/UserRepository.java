package in.ac.bitspilani.webapp.user;

import in.ac.bitspilani.webapp.category.Category;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Integer> {

    public User findById(Integer id);

     User findByEmail(String email);

     User save(User user);
}
