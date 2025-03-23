package com.proyectojr.electricalsupplies.infrastructure.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Proporciona una conexión a la base de datos MySQL.
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/electricalsupplies";
    private static final String USER = "root"; 
    private static final String PASSWORD = "1093907566@"; 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }
}