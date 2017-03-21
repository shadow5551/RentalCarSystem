package main.java.by.training.nc.dev3.model;

import java.io.Serializable;


public class User implements Serializable {
    private String login;
    private String password;
    private String numberOfPassport;
    private String role;
    private transient static int counterOfNewAccounts=1;

    public User(String login, String password, String numberOfPassport, String role) {
        this.login = login;
        this.password = password;
        this.numberOfPassport = numberOfPassport;
        this.role = role;
        counterOfNewAccounts++;
    }

    public User(String login) {
        this.login = login;
    }

    public User() {
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
                .format("login: %5s password: %5s numberofPassport: %5s", login, password, numberOfPassport);
    }

    public void displayCountNewUsersPerSession(){
        System.out.printf("Id: %d \n", counterOfNewAccounts);
    }
}
