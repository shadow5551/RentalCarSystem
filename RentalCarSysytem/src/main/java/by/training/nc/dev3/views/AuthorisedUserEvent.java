package main.java.by.training.nc.dev3.views;

import main.java.by.training.nc.dev3.model.OrderStatus;
import main.java.by.training.nc.dev3.service.LoginServiceImpl;
import main.java.by.training.nc.dev3.service.UserServiceImpl;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.User;


import java.util.Scanner;

/**
 * Created by dima on 16.3.17.
 */
public class AuthorisedUserEvent extends AuthorisedEvent{
    private Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private UserServiceImpl userService;

    public AuthorisedUserEvent(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean getAuthorisedUserEvent() {
        userService = new UserServiceImpl(currentUser);
        boolean flag = true;
        String choice;
        while (flag) {
            getAuthorizedUserAbilities();
            flag = true;
            choice = scanner.nextLine();
            getUserOrder(currentUser);
            switch (choice) {
                case "1":
                    bookingNewCar();
                    break;
                case "2":
                    payForDamage();
                    break;
                case "3":
                    bookedCarInProgress();
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
                    rechargeBalance();
                    break;
                case "8":
                    getBalance();
                    break;
                case "9":
                    flag = false;
                    break;
                default:
                    System.out.println("Введена неправильная позиция!");
            }
        }
        return true;
    }

    private void closedOrders() {
        if (userService.getListForCategory(super.ordersList,OrderStatus.REJECTED) ||
        userService.getListForCategory(super.ordersList,OrderStatus.CLOSED)){
            System.out.println("Нет завершенных заказов");
        }
    }

    private void rentedCar() {
        if (userService.getListForCategory(super.ordersList,OrderStatus.RENTED)){
            userService.payForRepairCar(super.ordersList);
        }else {
            System.out.println("Нет машин, которые вы сдали");
        }
    }

    private void rechargeBalance() {
        userService.rechargeBalance();

    }

    private void getBalance() {
        System.out.println("Ваш баланс составляет: "+currentUser.getBalance() + " USD");
    }

    @Override
    void getUserOrder(User user) {
        super.getUserOrder(user);
    }

    @Override
    void getAllCars() {
        super.getAllCars();
    }

    private void bookingNewCar() {
        getAllCars();
        if (super.carsList.size()!=0) {
            userService.bookingNewCar();
        }else {
            System.out.println("Доступных машин нет");
        }
    }

    private void payForDamage(){
        getUserOrder(currentUser);
        for (Order order:super.ordersList){
            if (order.getStatus().equals(OrderStatus.REPAIRED)){
                userService.payForRepairCar(ordersList);
                break;
            }
        }
    }
    private void onHandsCar() {
        if (userService.getListForCategory(super.ordersList,OrderStatus.ACCEPTED)){
            userService.userOrders(OrderStatus.ACCEPTED);
        }else {
            System.out.println("Нет машин, которые вы арендуете");
        }
    }

    private void bookedCarInProgress(){
        getUserOrder(currentUser);
        userService.getListForCategory(super.ordersList,OrderStatus.PROCESS);
    }

    private void getAuthorizedUserAbilities(){
        System.out.println("1.Забронировать машину");
        System.out.println("2.Оплатить повреждения");
        System.out.println("3.Ожидающие обработки заказы");
        System.out.println("4.Авто в пользовании");
        System.out.println("5.Сданные авто");
        System.out.println("6.Завершенные заказы");
        System.out.println("7.Пополнить баланс");
        System.out.println("8.Просмотреть баланс");
        System.out.println("9.Выход");
    }

    public static User getCurrentUser(){
        return currentUser;
    }
}
