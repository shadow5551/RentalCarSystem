package main.java.by.training.nc.dev3.file;


import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.interfaces.Reader;
import main.java.by.training.nc.dev3.model.Order;
import main.java.by.training.nc.dev3.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 15.3.17.
 */

public class ReadFile implements Reader,Serializable {
    private List<User> userList = new ArrayList<>();
    private List<Car> carList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

    @Override
    public void readItem(String nameOfFile) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(nameOfFile));
            while (true) {
                try {
                    if (nameOfFile.equals("User.txt")){
                        userList.add((User) in.readObject());
                    }
                    if (nameOfFile.equals("Car.txt")){
                        carList.add((Car) in.readObject());
                    }
                    if (nameOfFile.equals("Order.txt")){
                        orderList.add((Order) in.readObject());
                    }
                } catch (ClassNotFoundException e) {
                    in.close();
                    break;
                }
            }
        } catch (FileNotFoundException | EOFException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Car> getCarList() {
        return carList;
    }
}
