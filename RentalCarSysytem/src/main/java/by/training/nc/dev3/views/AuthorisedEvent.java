package main.java.by.training.nc.dev3.views;

import main.java.by.training.nc.dev3.dao.CarDaoImpl;
import main.java.by.training.nc.dev3.dao.OrderDaoImpl;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;

import java.util.ArrayList;
import java.util.List;

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
                System.out.println(i + 1 + ". " + carsList.get(i).getBrand() + " " + carsList.get(i).getModel());
            }
        }
    }

    void getAllOrders() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        ordersList = orderDao.getAll();
        if (ordersList.isEmpty()) {
            System.out.println("Заказов нет");
        } else {
            for (int i = 0; i < ordersList.size(); i++) {
                System.out.println(i + 1 + ". " + ordersList.get(i).getBrand() + " " + ordersList.get(i).getModel() + " " +
                                " " + ordersList.get(i).getDateofRefund() + " " + ordersList.get(i).getStatus());
            }
        }
    }


}
