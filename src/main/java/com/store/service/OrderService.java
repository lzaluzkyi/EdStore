package com.store.service;

import com.store.entity.Item;
import com.store.entity.Order;
import com.store.entity.User;

import java.util.List;

public interface OrderService {

    Order buy(User user , List<Item> itemsToBuy);

    List<Order> getAll();
}
