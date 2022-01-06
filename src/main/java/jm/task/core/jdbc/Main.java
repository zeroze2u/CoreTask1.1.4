package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService user = new UserServiceImpl();

    public static void main(String[] args) {
        user.createUsersTable();
        user.saveUser("Аделина", "Файзуллина", (byte)19);
        user.saveUser("Иван", "Иванов", (byte)22);
        user.saveUser("Вася", "Пупкин", (byte)33);
        user.saveUser("Имя", "Фамм", (byte)44);
        for(User all : user.getAllUsers()){
            System.out.println(all);
        }
        System.out.println("Удалим пользователя 2");
        user.removeUserById(2);
        for(User all : user.getAllUsers()){
            System.out.println(all);
        }
        System.out.println("Очистим и удалим таблицу");
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
