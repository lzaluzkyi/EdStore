package com.store.service.impl;

import com.store.dao.ItemDAO;
import com.store.dao.impl.ItemDAOImpl;
import com.store.entity.Category;
import com.store.entity.Item;
import com.store.service.ItemService;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO;

    @Autowired
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public Item create(Item item) {
        if (item.getId() != null) {
            throw new IllegalArgumentException("On method create we must didnt have id");
        }
        if (item.getName().startsWith("H")) {
            item.setCreditAvailable(true);
        }
        return save(item);
    }

    @Override
    public Item update(Item item) {
        if (item.getId() == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
        Item existItem = findById(item.getId());
        if (existItem != null) {
            return save(item);
        }
        throw new IllegalArgumentException("We cant find item with id " + item.getId());
    }

    @Override
    public Item findById(Long id) {
        return itemDAO.findById(id);
    }

    @Override
    public List<Item> findByCategory(Category category) {
        return itemDAO.findByCategory(category);
    }

    @Override
    public List<Item> findByName(String startWith) {
        if (startWith.isEmpty()) {
            throw new IllegalArgumentException("startWith cant be empty");
        }
        return itemDAO.findByName(startWith);
    }

    private Item save(Item item) {
        return itemDAO.save(item);
    }
}
