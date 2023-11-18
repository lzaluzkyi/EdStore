package service;

import entity.Category;
import entity.Item;

import java.util.List;

public interface ItemService {

    Item create(Item item);

    Item update(Item item);

    Item findById(Long id);

    List<Item> findByCategory(Category category);

    List<Item> findByName(String startWith);


}
