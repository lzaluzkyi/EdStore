package com.store.dao;

import com.store.entity.Category;
import com.store.entity.Item;

import java.util.List;

public interface ItemDAO {

    Item update(Item item);
    Item save(Item item);

    Item findById(Long id);

    List<Item> findByCategory(Category category);

    List<Item> findByName(String startWith);

    List<Item> getAll();

    void delete(Long itemId);
}
