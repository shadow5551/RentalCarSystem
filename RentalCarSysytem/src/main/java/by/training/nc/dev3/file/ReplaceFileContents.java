package main.java.by.training.nc.dev3.file;

import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.User;

import java.util.List;

/**
 * Created by dima on 27.3.17.
 */
public class ReplaceFileContents {

    public void replaceUser(String fileName, User user) {
        WriteFile writeFile = new WriteFile();
        ReadFile readFile = new ReadFile();
        readFile.readItem(fileName);
        List<User> usersList = readFile.getUserList();
        writeFile.delete(fileName);
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).equals(user))
            {
                usersList.set(i,user);
            }
        }
        for (User tempUser : usersList) {
            writeFile.writeItem(tempUser, fileName);
        }
    }

    public void replaceOrder(String fileName,Order order){
        WriteFile writeFile = new WriteFile();
        ReadFile readFile = new ReadFile();
        readFile.readItem(fileName);
        List<Order> ordersList = readFile.getOrderList();
        writeFile.delete(fileName);
        for (int i = 0; i < ordersList.size(); i++) {
            if (ordersList.get(i).equals(order))
            {
                ordersList.set(i,order);
            }
        }
        for (Order tempOrder : ordersList) {
            writeFile.writeItem(tempOrder, fileName);
        }
    }

    public void replaceCar(String fileName, Car car) {
        WriteFile writeFile = new WriteFile();
        ReadFile readFile = new ReadFile();
        readFile.readItem(fileName);
        List<Car> carsList = readFile.getCarList();
        writeFile.delete(fileName);
        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).equals(car))
            {
                carsList.set(i,car);
            }
        }
        for (Car tempCar : carsList) {
            writeFile.writeItem(tempCar, fileName);
        }
    }
}
