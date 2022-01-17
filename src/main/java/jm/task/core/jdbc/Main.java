package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Аделина", "ФФ", (byte)19);
        userService.saveUser("Иван", "Иванов", (byte)22);
        userService.saveUser("Вася", "Пупкин", (byte)33);
        userService.saveUser("Имя", "Фамм", (byte)44);
        for(User all : userService.getAllUsers()){
            System.out.println(all);
        }
        System.out.println("Удалим пользователя 2");
        userService.removeUserById(2);
        for(User all : userService.getAllUsers()){
            System.out.println(all);
        }
        System.out.println("Очистим и удалим таблицу");
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
