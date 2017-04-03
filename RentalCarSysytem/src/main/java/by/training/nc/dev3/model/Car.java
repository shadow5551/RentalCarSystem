package main.java.by.training.nc.dev3.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by dima on 17.3.17.
 */
public class Car implements Serializable{
    private int idCar;
    private String brand;
    private String model;
    private int countOfCars;
    private int pricePerDay;


    public Car(int idCar, String brand, String model, int countOfCars, int pricePerDay) {
        this.idCar = idCar;
        this.brand = brand;
        this.model = model;
        this.countOfCars = countOfCars;
        this.pricePerDay = pricePerDay;
    }

    public Car(String brand, String model, int pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
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
    public int hashCode() {
        return 76+13*idCar;
    }

    /*@Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if(this.getClass() != other.getClass()) return false;
        Car otherObj = (Car) other;
        if (!Objects.equals(this.getBrand(), otherObj.getBrand())) return false;
        if (!Objects.equals(this.getModel(), otherObj.getModel())) return false;
        if (!Objects.equals(this.getPricePerDay(),otherObj.getPricePerDay())) return false;
        return true;
    }*/

    @Override
    public String toString() {
        return String
                .format("brand: %5s model: %5s count:%5s price: %5s", brand, model, countOfCars,pricePerDay);
    }
}
