package service.impl;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import entity.Item;
import entity.Order;
import entity.Status;
import entity.User;
import service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = new OrderDAOImpl();

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
