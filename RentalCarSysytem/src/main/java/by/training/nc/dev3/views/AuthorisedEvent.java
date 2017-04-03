package main.java.by.training.nc.dev3.views;

import main.java.by.training.nc.dev3.dao.CarDaoImpl;
import main.java.by.training.nc.dev3.dao.OrderDaoImpl;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by dima on 18.3.17.
 */

abstract class AuthorisedEvent  {
    List<Car> carsList = new ArrayList<>();
    List<Order> ordersList = new ArrayList<>();

    void getAllCars() {
        CarDaoImpl carDao = new CarDaoImpl();
        carsList = carDao.getAll();
        if (carsList.isEmpty()) {
            System.out.println("Машин в автопарке нет");
        } else {
            for (int i = 0; i < carsList.size(); i++) {
                System.out.println(i + 1 + ". " + carsList.get(i).toString());
            }
        }
    }

    void getAllOrders() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        ordersList = orderDao.getAll();
    }

    void getUserOrder(User user){
        OrderDaoImpl orderDao = new OrderDaoImpl();
        ordersList = orderDao.getAll();
        Iterator<Order> iter = ordersList.iterator();
        while (iter.hasNext()) {
            String order = iter.next().getUser().getLogin();
            if (!order.equals(user.getLogin())) {
                iter.remove();
            }
        }
    }

}
