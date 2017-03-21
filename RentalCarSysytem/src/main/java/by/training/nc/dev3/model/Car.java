package main.java.by.training.nc.dev3.model;

import java.io.Serializable;

/**
 * Created by dima on 17.3.17.
 */
public class Car implements Serializable{
    private String brand;
    private String model;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = Integer.parseInt(prime * result + brand);
        result = Integer.parseInt(prime * result + model);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (brand != other.brand)
            return false;
        if (model != other.model)
            return false;
        return true;
    }
}
