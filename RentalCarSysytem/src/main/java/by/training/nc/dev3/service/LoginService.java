package main.java.by.training.nc.dev3.service;

import main.java.by.training.nc.dev3.model.User;

/** Интерфейс служит получения ссылки на объект класса User
 */

public interface LoginService {
    User getCurrentUser();
}
