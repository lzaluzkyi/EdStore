package service;

import entity.Item;
import entity.Order;
import entity.User;

import java.util.List;

public interface OrderService {

    Order buy(User user , List<Item> itemsToBuy);

    List<Order> getAll();
}
