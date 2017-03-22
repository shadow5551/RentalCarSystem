package main.java.by.training.nc.dev3.views;

import main.java.by.training.nc.dev3.service.UserServiceImpl;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.User;

import java.util.Scanner;

/**
 * Created by dima on 16.3.17.
 */
public class AuthorisedUserEvent extends AuthorisedEvent{
    private Scanner scanner = new Scanner(System.in);
    private UserServiceImpl userService = new UserServiceImpl();

    public boolean getAuthorisedUserEvent(User user) {
        boolean flag = true;
        String choice;
        while (flag) {
            getAuthorizedUserAbilities();
            flag = true;
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    bookingNewCar(user);
                    break;
                case "2":
                    payForDamage();
                    break;
                case "3":
                    bookedCar(user);
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

    @Override
    void getUserOrder(User user) {
        super.getUserOrder(user);
    }

    @Override
    void getAllCars() {
        super.getAllCars();
    }

    private void bookingNewCar(User user) {
        getAllCars();
        if (super.carsList.size()!=0) {
            userService.bookingNewCar(user);
        }
    }

    private void payForDamage(){
        System.out.println("pay");
    }

    private void bookedCar(User user){
        getUserOrder(user);
        for (Order order:super.ordersList){
                System.out.println(order.toString());
        }
    }

    private void getAuthorizedUserAbilities(){
        System.out.println("1.Забронировать машину");
        System.out.println("2.Оплатить повреждения");
        System.out.println("3.Взятые в прокат авто");
        System.out.println("4.Выход");
    }
}
