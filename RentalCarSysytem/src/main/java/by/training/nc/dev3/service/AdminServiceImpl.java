package main.java.by.training.nc.dev3.service;

import main.java.by.training.nc.dev3.dao.CarDaoImpl;
import main.java.by.training.nc.dev3.dao.OrderDaoImpl;
import main.java.by.training.nc.dev3.exception.CustomGenericException;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.OrderStatus;

import java.util.*;

/**
 * Created by dima on 18.3.17.
 */
public class AdminServiceImpl implements AdminService {
    private CarDaoImpl carDao = new CarDaoImpl();
    private List<Order> orderList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private ReturnService returnService = new ReturnService();


    /**
     * Метод пытается создать объект класса Car
     *
     * @return
     * @throws CustomGenericException
     */
    @Override
    public boolean addNewCar() throws CustomGenericException {
        if (!carDao.create()) {
            return false;
        } else return true;
    }

    /**
     * Метод получения объекта заказа по id
     */
    @Override
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
                while (!scanner.hasNextInt())
                {
                    System.out.println("Повторите ввод");
                    scanner.next();
                }
                    int idOrder = scanner.nextInt() - 1;
                    if ((orderList.size() > idOrder) & (idOrder >= 0)) {
                        if (orderStatus.equals(orderList.get(idOrder).getStatus()))
                            if (orderStatus.equals(OrderStatus.PROCESS)) {
                                actionWithInProgressOrder(orderList.get(idOrder), idOrder);
                            }
                        if (orderStatus.equals(OrderStatus.RENTED)) {
                            actionWithRentedOrder(orderList.get(idOrder), idOrder);
                        }
                        break;
                    }
                    System.out.println("Такого id нет в списке!");
            } while (true);
        }
    }

    private void actionWithRentedOrder(Order order, int idOrder) {
        boolean flag = true;
        String choice;
        while (flag) {
            flag = false;
            infoRentedOrder();
            choice = scanner.next();
            switch (choice) {
                case "1":
                    issueInvoice(order, idOrder);
                    break;
                case "2":
                    setStatusOrder(order, idOrder, OrderStatus.CLOSED);
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

    /**
     * Метод обрабатывает действия пользователя
     *
     * @param order   - заказ
     * @param idOrder - ключ заказа
     */
    private void actionWithInProgressOrder(Order order, int idOrder) {
        boolean flag = true;
        String choice;
        while (flag) {
            flag = false;
            infoProgressOrder();
            choice = scanner.next();
            switch (choice) {
                case "1":
                    setStatusOrder(order, idOrder, OrderStatus.ACCEPTED);
                    break;
                case "2":
                    rejectOrder(order, idOrder);
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

    private void setStatusOrder(Order order, int idOrder, OrderStatus orderStatus) {
        order.setStatus(orderStatus);
        orderList.set(idOrder, order);
        saveChanges();
    }

    /**
     * Метод для изменения статуса забронированной машины
     * @param order   - заказ
     * @param idOrder - ключ заказа
     */
    public void issueInvoice(Order order, int idOrder) {
        System.out.println(" Для снятия деньги за ремонт нажмите 1 , для ");
        if (scanner.next().equals("1")) {
            System.out.print("Введите счет за ремонт ");
            while (!scanner.hasNextInt()) scanner.next();
            int price = scanner.nextInt();
            System.out.println("Укажите причину взымания платы");
            order.setClarification(scanner.nextLine());
            order.setStatus(OrderStatus.REPAIRED);
            order.setRepairPrice(price);
            setStatusOrder(order, idOrder, order.getStatus());
        /*order.setStatus(OrderStatus.REPAIRED);
        orderList.set(idOrder, order);
        saveChanges();*/
        }
    }


    /**
     * Метод для изменения статуса заказа на "REFUSED"
     *
     * @param order   - заказ
     * @param idOrder - ключ заказа
     */
    private void rejectOrder(Order order, int idOrder) {
        System.out.println("Укажите причину отказа");
        order.setClarification(scanner.nextLine());
        returnService.returnMoneyAndCar(order);
        order.setStatus(OrderStatus.REJECTED);
        setStatusOrder(order, idOrder, order.getStatus());
        /*order.setStatus(OrderStatus.REJECTED);
        orderList.set(idOrder, order);
        saveChanges();*/
    }


    /**
     * Метод для сохранения измененных объектов класса Order в файл
     */
    void saveChanges() {
        WriteFile writeFile = new WriteFile();
        writeFile.delete("Order.txt");
        writeFile.writeCollection(orderList, "Order.txt");
    }

    /**
     * Метод для предоставления информации по возможным действиям пользователя
     */
    void infoProgressOrder() {
        System.out.println("Выберите что сделать с заказом");
        System.out.println("1.разрешить");
        System.out.println("2.отказать");
        System.out.println("3.назад");
    }

    void infoRentedOrder() {
        System.out.println("Выберите что сделать с заказом");
        System.out.println("1.взять за ремонт");
        System.out.println("2.завершить заказ");
        System.out.println("3.назад");
    }

    public boolean getListForCategory(List<Order> ordersList, OrderStatus orderStatus) {
        boolean flag = false;
        for (Order order : ordersList) {
            if (order.getStatus().equals(OrderStatus.REJECTED) || order.getStatus().equals(OrderStatus.REPAIRED)) {
                System.out.println("user:" + order.getUser().getLogin() + "  " + order.toString() + " reason: " + order.getClarification());
                flag=true;
            } else if (order.getStatus().equals(orderStatus)) {
                    System.out.println("user:" + order.getUser().getLogin() + "  " + order.toString());
                    flag = true;
            }
        }
        return flag;
    }
}
