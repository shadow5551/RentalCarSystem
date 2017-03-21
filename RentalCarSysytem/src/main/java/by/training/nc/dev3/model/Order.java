package main.java.by.training.nc.dev3.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dima on 19.3.17.
 */
public class Order implements Serializable {
    private int idOrder;
    private Car car;
    private User user;
    private Date dateofRefund;
    private String status;

    public Order(int idOrder,Car car, User user, Date dateofRefund, String status) {
        this.idOrder = idOrder;
        this.car = car;
        this.user = user;
        this.dateofRefund = dateofRefund;
        this.status = status;
    }

    public Order() {
    }


    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateofRefund() {
        return dateofRefund;
    }

    public void setDateofRefund(Date dateofRefund) {
        this.dateofRefund = dateofRefund;
    }

    @Override
    public String toString() {
        return String
                .format("brand: %5s model: %5s date: %5s status: %5s ", car.getBrand(), car.getModel(), dateofRefund,status);
    }
}
