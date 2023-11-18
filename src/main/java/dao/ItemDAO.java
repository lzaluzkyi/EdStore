package dao;

import entity.Category;
import entity.Item;

import java.util.List;

public interface ItemDAO {

    Item save(Item item);

    Item findById(Long id);

    List<Item> findByCategory(Category category);

    List<Item> findByName(String startWith);

}
