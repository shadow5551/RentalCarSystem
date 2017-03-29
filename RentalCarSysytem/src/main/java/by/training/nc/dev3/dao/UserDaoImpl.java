package main.java.by.training.nc.dev3.dao;

import main.java.by.training.nc.dev3.file.ReadFile;
import main.java.by.training.nc.dev3.file.WriteFile;
import main.java.by.training.nc.dev3.model.User;
import main.java.by.training.nc.dev3.validator.SighUpValidator;

import java.util.*;


/**
 * Created by dima on 18.3.17.
 */
public class UserDaoImpl implements Dao {
    private ReadFile readFile = new ReadFile();
    private Map<String, String> hashmap = new HashMap<String, String>();

    @Override
    public List<User> getAll() {
        readFile.readItem("User.txt");
        return readFile.getUserList();
    }



    @Override
    public void update(Object object) {
        User user = (User) object;
        WriteFile writeFile = new WriteFile();
        ReadFile readFile = new ReadFile();
        readFile.readItem("User.txt");
        List<User> usersList = readFile.getUserList();
        writeFile.delete("User.txt");
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).equals(user))
            {
                usersList.set(i,user);
            }
        }
        for (User tempUser : usersList) {
            writeFile.writeItem(tempUser, "User.txt");
        }
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean create() {
        User user = null;
        WriteFile writeFile = new WriteFile();
        Scanner in = new Scanner(System.in);
        SighUpValidator sighUpValidator = new SighUpValidator();
        try {
            do {
                System.out.println("Логин");//MI5465678
                hashmap.put("login", in.next());
                System.out.println("Пароль");
                hashmap.put("password", in.next());
                System.out.println("Паспорт");
                hashmap.put("passport", in.next());
                System.out.println("Роль");
                hashmap.put("role", in.next());
                user = new User(hashmap.get("login"), hashmap.get("password"), hashmap.get("passport"),hashmap.get("role"),1000);
            } while (!sighUpValidator.validate(user));
        } catch (Exception e) {
            return false;
        } finally {
            writeFile.writeItem(user,"User.txt");
        }
        return true;
    }
}
