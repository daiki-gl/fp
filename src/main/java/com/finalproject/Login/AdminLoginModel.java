package com.finalproject.Login;

import java.sql.*;

import com.finalproject.dbUnit.dbConnection;

public class AdminLoginModel {
    Connection conn = null;

    public AdminLoginModel() {
        this.conn = dbConnection.getConnection();
        if (this.conn == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.conn != null;
    }

    public boolean isLogin(String username, String password) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM login_tbl WHERE login_name = ? AND password = ?";

        try {
            statement = this.conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
