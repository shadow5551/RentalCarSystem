package main.java.by.training.nc.dev3.dao;

import main.java.by.training.nc.dev3.file.ReadFile;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.Car;

import java.util.*;

/**
 * Created by dima on 19.3.17.
 */
public class CarDaoImpl implements Dao{
    private Map<String, String> hashmap = new HashMap<>();
    private List<Car> carList = new ArrayList<>();

    @Override
    public List<Car> getAll() {
        ReadFile readFile = new ReadFile();
        readFile.readItem("Car.txt");
        return readFile.getCarList();
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean create() {
        Car car;
        CarDaoImpl carDao = new CarDaoImpl();
        carList = carDao.getAll();
        WriteFile writeFile = new WriteFile();
        writeFile.delete("Car.txt");
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Марка");
            hashmap.put("brand", in.next());
            System.out.println("Модель");
            hashmap.put("model", in.next());
            car = new Car(carList.size(),hashmap.get("brand"), hashmap.get("model"),  1);
            if (carList.size()==0){
            carList.add(car);
            }
            else {
                boolean flag = false;
                for (int i = 0; i < carList.size(); i++) {
                    if (carList.get(i).getBrand().equals(hashmap.get("brand")) && carList.get(i).getModel().equals(hashmap.get("model"))) {
                        car = new Car(i,hashmap.get("brand"), hashmap.get("model"), carList.get(i).getCountOfCars() + 1);
                        carList.set(i, car);
                        flag = true;
                    }
                }
                if (!flag){
                    carList.add(car);
                }
            }
        } catch (Exception e) {
            return false;
        } finally {
            for (Car tempCar : carList){
            writeFile.writeItem(tempCar,"Car.txt");}
        }
        return true;
    }
}
