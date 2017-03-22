package main.java.by.training.nc.dev3.model;

import java.io.Serializable;

/**
 * Created by dima on 17.3.17.
 */
public class Car implements Serializable{
    private int idCar;
    private String brand;
    private String model;
    private int countOfCars;


    public Car(int idCar, String brand, String model, int countOfCars) {
        this.idCar = idCar;
        this.brand = brand;
        this.model = model;
        this.countOfCars = countOfCars;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCountOfCars() {
        return countOfCars;
    }

    public void setCountOfCars(int countOfCars) {
        this.countOfCars = countOfCars;
    }




    @Override
    public String toString() {
        return String
                .format("brand: %5s model: %5s count:%5s ", brand, model, countOfCars);
    }
}
