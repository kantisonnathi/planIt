package in.ac.bitspilani.webapp.item;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ItemRepository extends Repository<Item, Integer> {

    //void save(Item item) throws DataAccessException;

    List<Item> findByCategoryId(Integer CategoryId);

    Item findById(Integer ItemId);

    void save(Item item);

    void deleteById(int ItemId);
}
