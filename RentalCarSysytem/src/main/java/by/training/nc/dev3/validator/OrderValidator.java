package main.java.by.training.nc.dev3.validator;

import main.java.by.training.nc.dev3.dao.CarDaoImpl;
import main.java.by.training.nc.dev3.exception.CustomGenericException;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;

import java.util.List;

/**
 * Created by dima on 19.3.17.
 */
public class OrderValidator  {

    public boolean validate(int idCar) throws CustomGenericException {
        CarDaoImpl carDao = new CarDaoImpl();
        List<Car> carList = carDao.getAll();
        if (carList.size()<idCar || idCar<0){
            System.out.println("Нет такой машины в наличии");
            return false;
        }else {
            return true;
        }
    }
}
