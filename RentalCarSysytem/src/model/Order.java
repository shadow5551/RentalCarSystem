package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dima on 19.3.17.
 */
public class Order implements Serializable {
    private int idOrder;
    private String login;
    private String brand;
    private String model;
    private Date dateofRefund;
    private String status;

    public Order(int idOrder, String login, String brand, String model, Date dateofRefund, String status) {
        this.idOrder = idOrder;
        this.login = login;
        this.brand = brand;
        this.model = model;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Date getDateofRefund() {
        return dateofRefund;
    }

    public void setDateofRefund(Date dateofRefund) {
        this.dateofRefund = dateofRefund;
    }
    @Override
    public String toString() {
        return String
                .format("brand: %5s model: %5s date: %5s status: %5s ", brand, model, dateofRefund,status);
    }
}
