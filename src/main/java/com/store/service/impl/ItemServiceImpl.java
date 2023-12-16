package com.store.service.impl;

import com.store.repository.CategoryRepository;
import com.store.repository.ItemRepository;
import com.store.dto.ItemDTO;
import com.store.entity.Category;
import com.store.entity.Item;
import com.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Item create(Item item) {
        if (item.getId() != null) {
            throw new IllegalArgumentException("On method create we must didnt have id");
        }
        if (item.getName().startsWith("H")) {
            item.setCreditAvailable(true);
        }
        return itemRepository.save(item);
    }



    @Override
    public Item create(ItemDTO itemDTO) {
        Item newItem = new Item();
        newItem.setCreditAvailable(itemDTO.getCreditAvailable());
        Optional<Category> category = categoryRepository.findById(itemDTO.getCategory().getId());
        newItem.setCategory(category.get());
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
            return itemRepository.save(item);
        }
        throw new IllegalArgumentException("We cant find item with id " + item.getId());
    }

    @Override
    public Item update(ItemDTO itemDTO) {
        Item newItem = new Item();
        newItem.setId(itemDTO.getId());
        newItem.setCreditAvailable(itemDTO.getCreditAvailable());
        Category category = categoryRepository.getById(itemDTO.getCategory().getId());
        newItem.setCategory(category);
        newItem.setName(itemDTO.getName());
        newItem.setDescription(itemDTO.getDescription());
        newItem.setStockCount(itemDTO.getStockCount());
        newItem.setPrice(itemDTO.getPrice());
        return update(newItem);
    }

    @Override
    @Transactional(readOnly = true)
    public Item findById(Long id) {
        Item item = itemRepository.findById(id).get();
        return item;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findByCategory(Category category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<Item> findByName(String startWith) {
        if (startWith.isEmpty()) {
            throw new IllegalArgumentException("startWith cant be empty");
        }
        return itemRepository.findByNameStartingWith(startWith);
    }

    @Override
    public Integer countByName(String startWith) {
        if (startWith.isEmpty()) {
            throw new IllegalArgumentException("startWith cant be empty");
        }
        return itemRepository.countByNameStartingWith(startWith);
    }

    @Override
    public void delete(Long itemId) {
        Item item = findById(itemId);
        itemRepository.delete(item);
    }
}
