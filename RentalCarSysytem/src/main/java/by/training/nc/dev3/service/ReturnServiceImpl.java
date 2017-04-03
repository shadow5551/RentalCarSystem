package main.java.by.training.nc.dev3.service;

import main.java.by.training.nc.dev3.dao.CarDaoImpl;
import main.java.by.training.nc.dev3.dao.UserDaoImpl;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.OrderStatus;
import main.java.by.training.nc.dev3.model.User;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by dima on 28.3.17.
 */

public class ReturnServiceImpl implements ReturnService {

    @Override
    public void returnMoneyAndCar(Order order){
        WriteFile writeFile = new WriteFile();
        UserDaoImpl userDao = new UserDaoImpl();
        CarDaoImpl carDao = new CarDaoImpl();
        List<User> userList = userDao.getAll();
        List<Car> carList = carDao.getAll();
        writeFile.delete("User.txt");
        writeFile.delete("Car.txt");
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(order.getUser())) {
                User user = userList.get(i);
                if (order.getStatus().equals(OrderStatus.REJECTED)) {
                    user.setBalance(userList.get(i).getBalance() + order.getPrice());
                }else if (order.getStatus().equals(OrderStatus.RENTED)){
                    Calendar calendar = new GregorianCalendar();
                    Date currentDate = calendar.getTime();
                    calendar.setTime(currentDate);
                    int diffInDays = (int) ((calendar.getTime().getTime() - order.getRefundDate().getTime()) / (1000 * 60 * 60 * 24));
                    user.setBalance(userList.get(i).getBalance() + order.getCar().getPricePerDay()*diffInDays);
                }
                userList.set(i,user);
            }
            writeFile.writeItem(userList.get(i), "User.txt");
        }
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).equals(order.getCar())) {
                Car car = carList.get(i);
                car.setCountOfCars(carList.get(i).getCountOfCars() + 1);
                carList.set(i,car);
            }
            writeFile.writeItem(carList.get(i), "Car.txt");
        }
    }

}
