package com.finalproject.dbUnit;

import java.sql.*;

public class dbConnection {
    Connection conn = null;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}