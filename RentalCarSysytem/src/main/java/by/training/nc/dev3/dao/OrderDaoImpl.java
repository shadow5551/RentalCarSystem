package main.java.by.training.nc.dev3.dao;

import main.java.by.training.nc.dev3.exception.CustomGenericException;
import main.java.by.training.nc.dev3.file.ReadFile;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.OrderStatus;
import main.java.by.training.nc.dev3.model.User;
import main.java.by.training.nc.dev3.validator.OrderValidator;
import main.java.by.training.nc.dev3.views.AuthorisedUserEvent;


import java.util.*;

/**
 * Created by dima on 20.3.17.
 */
public class OrderDaoImpl implements Dao {
    private Map<String, String> hashmap = new HashMap<>();
    private Scanner in = new Scanner(System.in);


    @Override
    public List<Order> getAll() {
        ReadFile readFile = new ReadFile();
        readFile.readItem("Order.txt");
        return readFile.getOrderList();
    }

    @Override
    public boolean create() throws CustomGenericException {
        User currentUser = AuthorisedUserEvent.getCurrentUser();
        List<Order> orderList = getAll();
        Order order;
        CarDaoImpl carDao = new CarDaoImpl();
        List<Car> carList = carDao.getAll();
        WriteFile writeFile = new WriteFile();
        Calendar calendar = new GregorianCalendar();
        Date currentDate = calendar.getTime();
        calendar.setTime(currentDate);
        OrderValidator orderValidator = new OrderValidator();
        do {
            System.out.println("Напишите id машины");
            hashmap.put("idCar", in.next());
            System.out.println("Выберите количество дней");
            hashmap.put("countOfDays", in.next());
        } while (!orderValidator.validate(hashmap));
        int idCar = Integer.parseInt(hashmap.get("idCar")) - 1;
        int countOfDays = Integer.parseInt(hashmap.get("countOfDays"));
        int priceOrder = carList.get(idCar).getPricePerDay() * countOfDays;
        if (currentUser.getBalance() <= countOfDays * carList.get(idCar).getPricePerDay()) {
            throw new CustomGenericException("Недостаточно средств!");
        }
        calendar.add(Calendar.DAY_OF_MONTH, countOfDays);
        order = new Order(orderList.size() + 1, carList.get(idCar), new User(currentUser.getLogin()),
                calendar.getTime(), OrderStatus.PROCESS,
                priceOrder);
        carList.get(idCar).setCountOfCars(carList.get(idCar).getCountOfCars() - 1);
        carDao.update(carList.get(idCar));
        currentUser.setBalance(currentUser.getBalance() - priceOrder);
        System.out.println("Заказ добавлен");
        writeFile.writeItem(order, "Order.txt");
        return true;
    }

    @Override
    public void update(Object object) {
        Order order = (Order) object ;
        WriteFile writeFile = new WriteFile();
        ReadFile readFile = new ReadFile();
        readFile.readItem("Order.txt");
        List<Order> ordersList = readFile.getOrderList();
        writeFile.delete("Order.txt");
        for (int i = 0; i < ordersList.size(); i++) {
            if (ordersList.get(i).equals(order))
            {
                ordersList.set(i,order);
            }
        }
        for (Order tempOrder : ordersList) {
            writeFile.writeItem(tempOrder,"Order.txt");
        }
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }
}
