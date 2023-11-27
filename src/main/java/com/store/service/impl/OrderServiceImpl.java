package com.store.service.impl;

import com.store.dao.OrderDAO;
import com.store.dao.impl.OrderDAOImpl;
import com.store.entity.Item;
import com.store.entity.Order;
import com.store.entity.Status;
import com.store.entity.User;
import com.store.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;


    @Override
    public Order buy(User user, List<Item> itemsToBuy) {
        Order order = new Order();
        order.setCreatedAt(new Date());
        order.setItems(new ArrayList<>());
        order.getItems().addAll(itemsToBuy);
        order.setStatus(Status.NEW);
        order.setUser(user);
        order.setPrice(collectPrice(itemsToBuy));
        Order savedOrder = orderDAO.create(order);
        return savedOrder;
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    private BigDecimal collectPrice(List<Item> items){
        BigDecimal result = BigDecimal.ZERO;
        for (Item item : items) {
            result = result.add(item.getPrice());
        }
        return result;
    }

}
