package com.finalproject.Signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.finalproject.dbUnit.dbConnection;

public class SignupModel {
    Connection conn = null;

    public SignupModel() {
        this.conn = dbConnection.getConnection();

        if (this.conn == null) {
            System.exit(1);
        }

    }

    public boolean isDatabaseConnected() {
        return this.conn != null;
    }

    public void isSignup(String username, String password) {
        PreparedStatement statement = null;

        String query = "INSERT INTO login_tbl (login_name, password) VALUES (?,?);";

        try {
            statement = this.conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
