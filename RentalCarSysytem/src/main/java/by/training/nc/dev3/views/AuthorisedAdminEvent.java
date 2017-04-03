package main.java.by.training.nc.dev3.views;

import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.OrderStatus;
import main.java.by.training.nc.dev3.service.AdminServiceImpl;
import main.java.by.training.nc.dev3.model.User;

import java.util.Scanner;

/**
 * Created by dima on 18.3.17.
 */
public class AuthorisedAdminEvent extends AuthorisedEvent{
    private Scanner scanner = new Scanner(System.in);
    private AdminServiceImpl adminService = new AdminServiceImpl();

    boolean getAuthorisedAdminEvent(User user) {
        boolean flag = true;
        String choice;
        while (flag) {
            getAuthorizedAdminAbilities();
            flag = true;
            choice = scanner.nextLine();
            getAllOrders();
            switch (choice) {
                case "1":
                    addNewCar();
                    break;
                case "2":
                    getAllCars();
                    break;
                case "3":
                    userProcessingOrders();
                    break;
                case "4":
                    onHandsCar();
                    break;
                case "5":
                    rentedCar();
                    break;
                case "6":
                    closedOrders();
                    break;
                case "7":
                    flag = false;
                    break;
                default:
                    System.out.println("Введена неправильная позиция!");
            }
        }
        return true;
    }

    private void rentedCar() {
        if(adminService.getListForCategory(super.ordersList,OrderStatus.RENTED)){
        adminService.userOrders(OrderStatus.RENTED);}
        else {
            System.out.println("Нет сданных авто");
        }
    }

    private void onHandsCar() {
        if(!adminService.getListForCategory(super.ordersList,OrderStatus.ACCEPTED))
        {
            System.out.println("Нет выданных авто");
        }
    }

    private void userProcessingOrders() {
        if (adminService.getListForCategory(super.ordersList,OrderStatus.PROCESS)){
        adminService.userOrders(OrderStatus.PROCESS);
        }else {
            System.out.println("Необработанных заказов в системе нет");
        }
    }

    private void closedOrders() {
        if (!adminService.getListForCategory(super.ordersList,OrderStatus.REJECTED) ||
        adminService.getListForCategory(super.ordersList,OrderStatus.CLOSED)){
            System.out.println("Нет завершенных заказов");
        }
    }


    @Override
    void getAllOrders() {
        super.getAllOrders();
    }

    @Override
    void getAllCars() {
        super.getAllCars();
    }

    private void addNewCar() {
        if (adminService.addNewCar()) {
            System.out.println("Машина добавлена");
        } else {
            System.out.println("Проблема с добавлением авто");
        }
    }

    private void getAuthorizedAdminAbilities() {
        System.out.println("1.Добавить машину");
        System.out.println("2.Просмотреть список машин в автопарке");
        System.out.println("3.Необработанные заказы клиентов");
        System.out.println("4.Авто в пользовании");
        System.out.println("5.Сданные авто");
        System.out.println("6.Завершенные закаы");
        System.out.println("7.Выход");
    }


}
