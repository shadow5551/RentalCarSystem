package main.java.by.training.nc.dev3.service;

import main.java.by.training.nc.dev3.dao.OrderDaoImpl;
import main.java.by.training.nc.dev3.model.User;

import java.util.Scanner;

/**
 * Created by dima on 17.3.17.
 */
public class UserServiceImpl implements UserService {
    private OrderDaoImpl orderDao = new OrderDaoImpl();


    /**
     * Метод принимает на вход ссылку на объект класса User
     * и пытается создать объект класса Order
     * @param currentUser
     * @return булевский результат добавления
     */
    @Override
    public boolean bookingNewCar(User currentUser) {
        if (!orderDao.create(currentUser)){
            return false;
        }else return true;
    }




}
