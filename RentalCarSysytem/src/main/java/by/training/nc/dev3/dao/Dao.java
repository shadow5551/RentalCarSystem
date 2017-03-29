package main.java.by.training.nc.dev3.dao;

import main.java.by.training.nc.dev3.model.User;

import java.util.List;

/**
 * Created by dima on 22.3.17.
 */
public interface Dao<T> {
    List<T> getAll();
    void update(T object);
    boolean delete(T object);
    boolean create();
}
