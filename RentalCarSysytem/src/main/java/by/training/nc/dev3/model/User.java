package main.java.by.training.nc.dev3.model;

import java.io.Serializable;
import java.util.Objects;


public class User implements Serializable,Comparable<User> {
    private String login;
    private String password;
    private String numberOfPassport;
    private String role;
    private int balance;

    public User(String login, String password, String numberOfPassport, String role , int balance) {
        this.login = login;
        this.password = password;
        this.numberOfPassport = numberOfPassport;
        this.role = role;
        this.balance = balance;

    }



    public User(String login) {
        this.login = login;
    }

    public User() {
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberOfPassport() {
        return numberOfPassport;
    }

    public void setNumberOfPassport(String numberOfPassport) {
        this.numberOfPassport = numberOfPassport;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String
                .format("login: %5s password: %5s numberPassport: %5s", login, password, numberOfPassport);
    }

    @Override
    public int compareTo(User o) {
        return login.compareTo(o.getLogin());
    }
}
