package main.java.by.training.nc.dev3.views;

import main.java.by.training.nc.dev3.service.AdminServiceImpl;
import main.java.by.training.nc.dev3.model.User;

import java.util.Scanner;

/**
 * Created by dima on 18.3.17.
 */
public class AuthorisedAdminEvent extends AuthorisedEvent{
    private Scanner scanner = new Scanner(System.in);
    private AdminServiceImpl adminService = new AdminServiceImpl();

    public boolean getAuthorisedAdminEvent(User user) {
        boolean flag = true;
        String choice;
        while (flag) {
            getAuthorizedAdminAbilities();
            flag = true;
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addNewCar();
                    break;
                case "2":
                    getAllCars();
                    break;
                case "3":
                    userOrders();
                    break;
                case "4":
                    flag = false;
                    break;
                default:
                    System.out.println("Введена неправильная позиция!");
            }
        }
        return true;
    }

    private void userOrders() {
        getAllOrders();
        if (super.ordersList.size()!=0) {
            System.out.println("Заказы Клиентов:");
            adminService.userOrders();
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
        System.out.println("3.Заказы клиентов");
        System.out.println("4.Выход");
    }


}
