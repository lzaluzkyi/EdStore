package com.store.service;

import com.store.dto.ItemDTO;
import com.store.entity.Category;
import com.store.entity.Item;

import java.util.List;

public interface ItemService {

    Item create(Item item);
    Item create(ItemDTO item);

    Item update(Item item);
    Item update(ItemDTO itemDTO);

    Item findById(Long id);
    List<Item> findAll();

    List<Item> findByCategory(Category category);

    List<Item> findByName(String startWith);


    void delete(Long itemId);
}
