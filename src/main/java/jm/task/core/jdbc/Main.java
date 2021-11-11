package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        User user1 = new User("Ann", "White", (byte) 19);
        User user2 = new User("Eva", "Brown", (byte) 42);
        User user3 = new User("John", "Doe", (byte) 99);
        User user4 = new User("Peter", "Smith", (byte) 50);
        //userService.dropUsersTable();

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем – " + user1.getName() + " добавлен в базу данных");
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем – " + user2.getName() + " добавлен в базу данных");
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем – " + user3.getName() + " добавлен в базу данных");
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем – " + user4.getName() + " добавлен в базу данных");

        List<User> allUsers = userService.getAllUsers();
        //allUsers.forEach(u -> System.out.print(u.getId() + " " + u.getName() + "\n"));
        allUsers.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
