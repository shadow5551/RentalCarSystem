package main.java.by.training.nc.dev3.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dima on 25.3.17.
 */
public class CarValidator {

    public boolean validate(Map<String,String> map) {
            try {
                Integer.parseInt(map.get("pricePerDay"));
            } catch (Exception e) {
                System.out.println("Ошибка при вводе цены за авто!");
                return false;
            }
        if (Integer.parseInt(map.get("pricePerDay"))<=0){
            System.out.println("Ошибка при вводе цены за авто!");
            return false;
        }
        return true;
    }
}
