package dao;

import entity.Order;

import java.util.List;

public interface OrderDAO {

    Order create(Order order);

    Order getById(Long id);

    List<Order> getByIds(List<Long> ids);

    List<Order> getAll();

}
