package main.java.by.training.nc.dev3.validator;

import main.java.by.training.nc.dev3.exceprion.CustomGenericException;
import main.java.by.training.nc.dev3.model.User;

/**
 * Created by dima on 16.3.17.
 */
public interface Validatable {
    boolean validate(Object object) throws CustomGenericException;
}
