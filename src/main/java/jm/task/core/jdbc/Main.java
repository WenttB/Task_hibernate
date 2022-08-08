package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Kim","Chen yn", (byte) 39);
        System.out.println("User с именем - Kim добавлен в базу данных");
        userService.saveUser("Gurbanguly","Berdymuhamedov",(byte) 64);
        System.out.println("User с именем - Gurbanguly добавлен в базу данных");
        userService.saveUser("Bashar","al Asad", (byte) 57);
        System.out.println("User с именем - Bashar добавлен в базу данных");
        userService.saveUser("Aleksandr","Lukashenko",(byte) 67);
        System.out.println("User с именем - Aleksandr добавлен в базу данных");
        List<User> uu = userService.getAllUsers();
        for (User user:uu) {
            System.out.println(user.toString());
        }
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSession();
    }
}
