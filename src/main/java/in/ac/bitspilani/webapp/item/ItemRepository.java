package in.ac.bitspilani.webapp.item;

import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ItemRepository extends Repository<Item, Integer> {


    List<Item> findByCategoryId(Integer categoryId);

    Item findById(Integer itemId);

    Item save(Item item);

    Long deleteById(Integer itemId);

    void delete(Item item);

    List<Item> findAllByName(String name);

    List<Item> findAllByDueDate(LocalDate dueDate);
}
