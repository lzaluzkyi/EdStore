package service.impl;

import dao.ItemDAO;
import dao.impl.ItemDAOImpl;
import entity.Category;
import entity.Item;
import service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO = new ItemDAOImpl();

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
