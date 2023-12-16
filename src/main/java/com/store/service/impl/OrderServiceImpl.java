package com.store.service.impl;

import com.store.repository.OrderRepository;
import com.store.entity.Item;
import com.store.entity.Order;
import com.store.entity.Status;
import com.store.entity.User;
import com.store.service.ItemService;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order buy(User user, List<Item> itemsToBuy) {
        Order order = new Order();
        order.setCreatedAt(new Date());
        order.setItems(new ArrayList<>());
        order.getItems().addAll(itemsToBuy);
        order.setStatus(Status.NEW);
        order.setUser(user);
        order.setPrice(collectPrice(itemsToBuy));
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    private BigDecimal collectPrice(List<Item> items){
        BigDecimal result = BigDecimal.ZERO;
        for (Item item : items) {
            result = result.add(item.getPrice());
        }
        return result;
    }

}
