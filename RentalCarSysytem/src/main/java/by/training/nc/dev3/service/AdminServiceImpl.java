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
public class AdminServiceImpl implements  AdminService {
    private CarDaoImpl carDao = new CarDaoImpl();
    private List<Order> orderList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    /**
     * Метод пытается создать объект класса Car
     * @return
     * @throws CustomGenericException
     */
    @Override
    public boolean addNewCar() throws CustomGenericException {
        if (!carDao.create()){
            return false;
        }else return true;
    }

    /**
     * Метод получения объекта заказа по id
     */
    @Override
    public void userOrders() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderList = orderDao.getAll();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер заказа:");
        do
        {
            if (in.hasNextInt()) {
                int idOrder = in.nextInt()-1;
                if ((orderList.size() < idOrder) || (idOrder < 0)) {
                    System.out.println("Повторите ввод");
                } else {
                    actionWithOrder(orderList.get(idOrder),idOrder);
                    break;
                }
            }
        }while(true);
    }

    /**
     *Метод обрабатывает действия пользователя
     * @param order - заказ
     * @param idOrder - ключ заказа
     */
    private void actionWithOrder(Order order,int idOrder) {
        WriteFile writeFile = new WriteFile();
        writeFile.delete("Order.txt");
        boolean flag = true;
        String choice;
        while (flag) {
            flag = true;
            info();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    applyOrder(order,idOrder);
                    break;
                case "2":
                    refuseOrder(order,idOrder);
                    break;
                case "3":
                    issueInvoice(order,idOrder);
                    break;
                case "4":
                    flag = false;
                    break;
                default:
                    System.out.println("Введена неправильная позиция!");
            }
        }
    }

    /**
     *Метод для изменения статуса забронированной машины
     * @param order - заказ
     * @param idOrder - ключ заказа
     */
    public void issueInvoice(Order order, int idOrder) {
        System.out.print("Введите счет за ремонт ");
        while (!scanner.hasNextInt()) scanner.next();
        int price = scanner.nextInt();
        System.out.println("Укажите причину взымания платы");
        order.setStatus(String.valueOf(OrderStatus.REPAIRED) + scanner.nextLine() + price + "$");
        orderList.set(idOrder,order);
        saveChanges();
    }

    /**
     *Метод для изменения статуса заказа на "REFUSED"
     * @param order - заказ
     * @param idOrder - ключ заказа
     */
    private void refuseOrder(Order order, int idOrder) {
        System.out.println("Укажите причину отказа");
        order.setStatus(String.valueOf(OrderStatus.REFUSED)+"(reason:"+scanner.nextLine()+")");
        orderList.set(idOrder,order);
        saveChanges();
    }

    /**
     *Метод для изменения статуса заказа на "ACCEPTED"
     * @param order - заказ
     * @param idOrder - ключ заказа
     */
    void applyOrder(Order order, int idOrder){
        order.setStatus(String.valueOf(OrderStatus.ACCEPTED));
        orderList.set(idOrder,order);
        saveChanges();
    }

    /**
     * Метод для сохранения измененных объектов класса Order в файл
     */
    void saveChanges(){
        WriteFile writeFile = new WriteFile();
        writeFile.writeCollection(orderList,"Order.txt");
    }

    /**
     * Метод для предоставления информации по возможным действиям пользователя
     */
    void info(){
        System.out.println("Выберите что сделать с заказом");
        System.out.println("1.разрешить");
        System.out.println("2.отказать");
        System.out.println("3.выставить счет");
        System.out.println("4.назад");
    }


}
