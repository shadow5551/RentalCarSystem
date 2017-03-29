package main.java.by.training.nc.dev3.dao;

import main.java.by.training.nc.dev3.file.ReadFile;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.Car;
import main.java.by.training.nc.dev3.validator.CarValidator;

import java.util.*;

/**
 * Created by dima on 19.3.17.
 */
public class CarDaoImpl implements Dao {
    private Map<String, String> hashmap = new HashMap<>();
    private List<Car> carList = new ArrayList<>();

    @Override
    public List<Car> getAll() {
        ReadFile readFile = new ReadFile();
        readFile.readItem("Car.txt");
        return readFile.getCarList();
    }

    @Override
    public void update(Object object) {
        Car car = (Car) object;
        WriteFile writeFile = new WriteFile();
        ReadFile readFile = new ReadFile();
        readFile.readItem("Car.txt");
        List<Car> carsList = readFile.getCarList();
        writeFile.delete("Car.txt");
        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).equals(car))
            {
                carsList.set(i,car);
            }
        }
        for (Car tempCar : carsList) {
            writeFile.writeItem(tempCar, "Car.txt");
        }
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean create() {
        Car car;
        CarValidator carValidator = new CarValidator();
        CarDaoImpl carDao = new CarDaoImpl();
        carList = carDao.getAll();
        WriteFile writeFile = new WriteFile();
        writeFile.delete("Car.txt");
        Scanner in = new Scanner(System.in);
        try {
            do {
                System.out.println("Марка");
                hashmap.put("brand", in.next());
                System.out.println("Модель");
                hashmap.put("model", in.next());
                System.out.println("Цена за день");
                hashmap.put("pricePerDay", in.next());
            } while (!carValidator.validate(hashmap));
            car = new Car(hashmap.get("brand"), hashmap.get("model"), Integer.parseInt(hashmap.get("pricePerDay")));
            boolean flag = false;
            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).equals(car)) {
                    car = new Car(i, hashmap.get("brand"), hashmap.get("model"), carList.get(i).getCountOfCars() + 1, Integer.parseInt(hashmap.get("pricePerDay")));
                    carList.set(i, car);
                    flag = true;
                }
            }
            if (!flag) {
                car = new Car(carList.size(), hashmap.get("brand"), hashmap.get("model"), 1, Integer.parseInt(hashmap.get("pricePerDay")));
                carList.add(car);
            }
        } catch (Exception e) {
            return false;
        } finally {
            for (Car tempCar : carList) {
                writeFile.writeItem(tempCar, "Car.txt");
            }
        }
        return true;
    }
}
