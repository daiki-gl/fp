package com.finalproject.Login;

import java.sql.*;

import com.finalproject.dbUnit.dbConnection;

public class LoginModel {
    Connection conn = null;

    public LoginModel() {
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

        String query = "SELECT * FROM teachers_tbl WHERE name = ? AND password = ?";

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

    public String getLoginId(String username, String password) {
        String teacher_id = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT teacher_id FROM teachers_tbl WHERE name = ? AND password = ?";

        try {

            statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                teacher_id = resultSet.getString(1);
            }
            return teacher_id;

        } catch (SQLException e) {
            e.printStackTrace();
            return teacher_id;
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