package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService u = new UserServiceImpl();
        u.createUsersTable();
        u.saveUser("Arthas", "Menethyl", (byte)40);
        u.saveUser("Liquid", "Snake", (byte) 32);
        u.saveUser("Johny","Hopkins", (byte)54);
        u.saveUser("Stanley", "Kubrick",(byte)12);
        System.out.println(u.getAllUsers());
        u.cleanUsersTable();
        u.dropUsersTable();
    }
}
