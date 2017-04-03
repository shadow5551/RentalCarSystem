package main.java.by.training.nc.dev3.service;

import main.java.by.training.nc.dev3.dao.OrderDaoImpl;
import main.java.by.training.nc.dev3.dao.UserDaoImpl;
import main.java.by.training.nc.dev3.exception.CustomGenericException;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.OrderStatus;
import main.java.by.training.nc.dev3.model.User;

import java.util.*;

/**
 * Created by dima on 17.3.17.
 */
public class UserServiceImpl implements UserService {
    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private User currentUser;
    private List<Order> orderList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private ReturnServiceImpl returnService = new ReturnServiceImpl();
    private UserDaoImpl userDao = new UserDaoImpl();


    public UserServiceImpl(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Метод пытается создать объект класса Order
     *
     * @return булевский результат добавления
     */
    @Override
    public boolean bookingNewCar() {
        try {
            orderDao.create();
            userDao.update(currentUser);
            return true;
        } catch (CustomGenericException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void userOrders(OrderStatus orderStatus) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderList = orderDao.getAll();
        WriteFile writeFile = new WriteFile();
        writeFile.delete("Order.txt");
        Scanner in = new Scanner(System.in);
        System.out.println("Для работы с заказами нажмите 1 , для выхода нажмите любую клавишу");
        if (in.next().equals("1")) {
            System.out.println("Введите номер заказа:");
            do {
                if (in.hasNextInt()) {
                    int idOrder = in.nextInt() - 1;
                    if ((orderList.size() > idOrder) & (idOrder >= 0)) {
                        if (orderStatus.equals(orderList.get(idOrder).getStatus()))
                            if (orderStatus.equals(OrderStatus.ACCEPTED)) {
                                actionWithAcceptedOrder(orderList.get(idOrder), idOrder);
                            }
                        break;
                    }
                    System.out.println("Повторите ввод");
                }
            } while (true);
        }
        saveChanges();
    }

    private void actionWithAcceptedOrder(Order order, int idOrder) {
        boolean flag = true;
        String choice;
        while (flag) {
            flag = false;
            infoAcceptedOrder();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    closeOrder(order, idOrder);
                    break;
                case "2":
                    extendOrder(order, idOrder);
                    break;
                case "3":
                    saveChanges();
                    break;
                default:
                    flag = true;
                    System.out.println("Введена неправильная позиция!");
            }
        }
    }

    private void closeOrder(Order order, int idOrder) {
        order.setStatus(OrderStatus.RENTED);
        returnService.returnMoneyAndCar(order);
        setStatusOrder(order, idOrder, order.getStatus());
    }

    private void extendOrder(Order order, int idOrder) {
        System.out.println("введите количество дополнительных дней");
        while (!scanner.hasNextInt()) scanner.next();
        int days = scanner.nextInt();
        if (currentUser.getBalance() - order.getCar().getPricePerDay() * days < 0) {
            System.out.println("Ага, бабки сначала кинб на карточку , а потом продлевай свой зказа");
        } else {
            currentUser.setBalance(currentUser.getBalance() - order.getCar().getPricePerDay() * days);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(order.getRefundDate());
            calendar.add(Calendar.DAY_OF_MONTH, days);
            order.setRefundDate(calendar.getTime());
            setStatusOrder(order, idOrder, order.getStatus());
        }
    }

    private void infoAcceptedOrder() {
        System.out.println("Выберите что сделать с заказом");
        System.out.println("1.завершить заказ и вернуть авто");
        System.out.println("2.продлить авто");
        System.out.println("3.назад");
    }


    @Override
    public boolean rechargeBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ведите количество USD");
        while (!scanner.hasNextInt()) scanner.next();
        int balance = scanner.nextInt();
        currentUser.setBalance(currentUser.getBalance() + balance);
        userDao.update(currentUser);
        return true;
    }

    public void payForRepairCar(List<Order> ordersList) {
        getListForCategory(ordersList, OrderStatus.REPAIRED);
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Для работы с заказами нажмите 1 , для выхода нажмите любую клавишу");
            if (in.next().equals("1")) {
                System.out.println("Введите номер заказа:");
                if (in.hasNextInt()) {
                    int idOrder = in.nextInt() - 1;
                    if ((ordersList.size() > idOrder) & (idOrder >= 0)) {
                        if (OrderStatus.REPAIRED.equals(ordersList.get(idOrder).getStatus())) {
                            if (currentUser.getBalance() < ordersList.get(idOrder).getRepairPrice()) {
                                System.out.println("Недостаточно средств для оплаты ремонта заказа №" + idOrder);
                            } else {
                                currentUser.setBalance(currentUser.getBalance() - ordersList.get(idOrder).getRepairPrice());
                                userDao.update(currentUser);
                                Order order = ordersList.get(idOrder);
                                order.setStatus(OrderStatus.CLOSED);
                                orderDao.update(order);
                                System.out.println("Оплата за ремонт произведена успешна");
                            }
                            break;
                        }
                    }
                    System.out.println("Повторите ввод");
                }
            } else break;
        } while (true);

    }

    private void setStatusOrder(Order order, int idOrder, OrderStatus orderStatus) {
        order.setStatus(orderStatus);
        orderList.set(idOrder, order);
    }

    void saveChanges() {
        WriteFile writeFile = new WriteFile();
        writeFile.delete("Order.txt");
        writeFile.writeCollection(orderList, "Order.txt");
    }

    public boolean getListForCategory(List<Order> ordersList, OrderStatus orderStatus) {
        boolean flag = false;
        for (Order order : ordersList) {
            if (order.getStatus().equals(orderStatus)) {
                if (order.getClarification()==null) {
                    System.out.println(order.toString());
                    flag = true;
                } else {
                    System.out.println(order.toString() + " reason: " + order.getClarification());
                    flag = true;
                }
            }
        }
        return flag;
    }
}
