package main.java.by.training.nc.dev3.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by dima on 19.3.17.
 */
public class Order implements Serializable {
    private int idOrder;
    private Car car;
    private User user;
    private Date refundDate;
    private OrderStatus status;
    private String clarification;
    private int price;
    private int repairPrice;

    public Order(int idOrder, Car car, User user, Date refundDate, OrderStatus status, int price) {
        this.idOrder = idOrder;
        this.car = car;
        this.user = user;
        this.refundDate = refundDate;
        this.status = status;
        this.price = price;
    }

    public Order() {
    }


    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getClarification() {
        return clarification;
    }

    public void setClarification(String clarification) {
        this.clarification = clarification;
    }


    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public int getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(int repairPrice) {
        this.repairPrice = repairPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if(this.getClass() != other.getClass()) return false;
        Order otherObj = (Order) other;
        if (!Objects.equals(this.getIdOrder(), otherObj.getIdOrder())) return false;
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy");
        return String
                .format("idOrder: %5s brand: %5s model: %5s date: %5s status: %5s price :%5s",idOrder, car.getBrand(), car.getModel(), date_format.format(refundDate),status,price);
    }
}
