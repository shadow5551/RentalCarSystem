package main.java.by.training.nc.dev3.service;

import main.java.by.training.nc.dev3.model.User;
import main.java.by.training.nc.dev3.validator.SignInValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dima on 18.3.17.
 */
public class LoginServiceImpl implements LoginService{
    private Map<String, String> hashmap = new HashMap<String, String>();

    /**
     * Метод для получения текущего пользователя
     * @return объект класса User
     */
    @Override
    public User getCurrentUser() {
        SignInValidator signInValidator = new SignInValidator();
        User user;
        Scanner in = new Scanner(System.in);
        System.out.println("Логин");//MI5465678,1234567890 -- admin
        hashmap.put("login", in.next());
        System.out.println("Пароль");
        hashmap.put("password", in.next());
        user = signInValidator.isExistingUser(hashmap.get("login"), hashmap.get("password"));
        return user;
    }
}
