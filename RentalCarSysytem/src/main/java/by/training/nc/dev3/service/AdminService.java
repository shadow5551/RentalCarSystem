package main.java.by.training.nc.dev3.service;

import main.java.by.training.nc.dev3.model.OrderStatus;

/** Интерфейс служит для добавления объектов класса Car в файл и
 * работы с объектами класса Order
 */

public interface AdminService {
    boolean addNewCar();
    void userOrders(OrderStatus orderStatus);
}
