package main.java.by.training.nc.dev3.dao;

import main.java.by.training.nc.dev3.file.ReadFile;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.OrderStatus;
import main.java.by.training.nc.dev3.model.User;
import main.java.by.training.nc.dev3.validator.OrderValidator;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dima on 20.3.17.
 */
public class OrderDaoImpl implements OrderDao {
    private Map<String, String> hashmap = new HashMap<>();
    private Scanner in = new Scanner(System.in);


    @Override
    public List<Order> getAll() {
        ReadFile readFile = new ReadFile();
        readFile.readItem("Order.txt");
        return readFile.getOrderList();
    }

    @Override
    public boolean create(User currentUser) {
        List<Order> orderList = getAll();
        Order order = null;
        CarDaoImpl carDao = new CarDaoImpl();
        List<Car> carList = carDao.getAll();
        WriteFile writeFile = new WriteFile();
        Calendar calendar = new GregorianCalendar();
        Date currentDate = calendar.getTime();
        calendar.setTime(currentDate);
        OrderValidator orderValidator = new OrderValidator();
        try {
            int idCar;
            do {
                System.out.println("Напишите id машины");
                while (!in.hasNextInt())
                    in.next();
                idCar = in.nextInt();
            }while (!orderValidator.validate(idCar));
                System.out.println("Выберите количество дней");
                while (!in.hasNextInt()) in.next();
                hashmap.put("countOfDays", String.valueOf(in.nextInt()));
                calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(hashmap.get("countOfDays")));
                order = new Order(orderList.size()+1,carList.get(idCar-1),new User(currentUser.getLogin()), calendar.getTime(), String.valueOf(OrderStatus.PROCESS));
                System.out.println("Заказ дабавлен");
        } catch (Exception e) {
            return false;
        } finally {
            writeFile.writeItem(order,"Order.txt");
        }
        return true;
    }

    @Override
    public Order update(Order order) {
        return null;
    }
}
