package main.java.by.training.nc.dev3.validator;

import main.java.by.training.nc.dev3.dao.OrderDaoImpl;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 28.3.17.
 */
public class EventOrderStatusValidator {
    private List<Order> ordersList = new ArrayList<>();

    public boolean validate(OrderStatus orderStatus, Order order){
        OrderDaoImpl orderDao = new OrderDaoImpl();
        ordersList = orderDao.getAll();
        if (order.getStatus().equals(orderStatus)){
            System.out.println("совпадают");
            return true;
        }else {
            System.out.println("несовпадают");
            return false;
        }
    }
}
