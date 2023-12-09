package com.store.service.impl;

import com.store.dao.ItemDAO;
import com.store.dao.impl.CategoryDAOImpl;
import com.store.dto.ItemDTO;
import com.store.entity.Category;
import com.store.entity.Item;
import com.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO;
    private CategoryDAOImpl categoryDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDAO, CategoryDAOImpl categoryDAO) {
        this.itemDAO = itemDAO;
        this.categoryDAO = categoryDAO;
    }


    @Override
    public Item create(Item item) {
        if (item.getId() != null) {
            throw new IllegalArgumentException("On method create we must didnt have id");
        }
        if (item.getName().startsWith("H")) {
            item.setCreditAvailable(true);
        }
        return itemDAO.save(item);
    }



    @Override
    public Item create(ItemDTO itemDTO) {
        Item newItem = new Item();
        newItem.setCreditAvailable(itemDTO.getCreditAvailable());
        newItem.setCategory(categoryDAO.findById(itemDTO.getCategory().getId()));
        newItem.setName(itemDTO.getName());
        newItem.setDescription(itemDTO.getDescription());
        newItem.setStockCount(itemDTO.getStockCount());
        newItem.setPrice(itemDTO.getPrice());
        return create(newItem);
    }

    @Override
    public Item update(Item item) {
        if (item.getId() == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
        Item existItem = findById(item.getId());
        if (existItem != null) {
            return itemDAO.update(item);
        }
        throw new IllegalArgumentException("We cant find item with id " + item.getId());
    }

    @Override
    public Item update(ItemDTO itemDTO) {
        Item newItem = new Item();
        newItem.setId(itemDTO.getId());
        newItem.setCreditAvailable(itemDTO.getCreditAvailable());
        Category category = categoryDAO.findById(itemDTO.getCategory().getId());
        newItem.setCategory(category);
        newItem.setName(itemDTO.getName());
        newItem.setDescription(itemDTO.getDescription());
        newItem.setStockCount(itemDTO.getStockCount());
        newItem.setPrice(itemDTO.getPrice());
        return update(newItem);
    }

    @Override
    public Item findById(Long id) {
        return itemDAO.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return itemDAO.getAll();
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

    @Override
    public void delete(Long itemId) {
        itemDAO.delete(itemId);
    }
}
