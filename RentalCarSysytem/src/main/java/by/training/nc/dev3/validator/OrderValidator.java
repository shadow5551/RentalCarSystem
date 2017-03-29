package main.java.by.training.nc.dev3.validator;

import main.java.by.training.nc.dev3.dao.CarDaoImpl;
import main.java.by.training.nc.dev3.exception.CustomGenericException;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 19.3.17.
 */
public class OrderValidator {

    public boolean validate(Map<String, String> map) throws CustomGenericException {
        try {
            Integer.parseInt(map.get("idCar"));
        } catch (Exception e) {
            System.out.println("Нет такой машины в наличии");
            return false;
        }
        try {
            Integer.parseInt(map.get("countOfDays"));
        } catch (Exception e) {
            System.out.println("Неверно введено количество дней");
            return false;
        }
        if (!validateIdCar(Integer.parseInt(map.get("idCar")))) {
            return false;
        }
        return true;
    }

    private boolean validateIdCar(int idCar) {
        CarDaoImpl carDao = new CarDaoImpl();
        List<Car> carList = carDao.getAll();
        if (carList.size() < idCar || idCar < 0) {
            System.out.println("Нет такой машины в наличии");
            return false;
        } else {
            return true;
        }
    }
}
