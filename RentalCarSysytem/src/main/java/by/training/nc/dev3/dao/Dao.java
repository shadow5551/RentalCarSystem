package main.java.by.training.nc.dev3.dao;

import java.util.List;

/**
 * Created by dima on 22.3.17.
 */
public interface Dao<T> {
    List<T> getAll();
    T update(T object);//при подключении к бд поменять на ключ сущности
    boolean delete(T object);
    boolean create();
}
