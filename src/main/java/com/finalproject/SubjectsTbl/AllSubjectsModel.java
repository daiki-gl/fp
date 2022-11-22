package com.finalproject.SubjectsTbl;

import java.sql.*;
import java.util.ArrayList;

import com.finalproject.dbUnit.dbConnection;

import javafx.collections.*;

public class AllSubjectsModel {
    Connection conn = null;
    private ObservableList<AllSubjectsData> allSubjectsDatas;

    public AllSubjectsModel() {
        this.conn = dbConnection.getConnection();
        if (this.conn == null) {
            System.exit(0);
        }
    }

    ArrayList<String> names = new ArrayList<>();

    public ArrayList<String> getSubjectsName() {
        String query = "SELECT * FROM subjects_tbl";
        this.allSubjectsDatas = FXCollections.observableArrayList();

        ResultSet resultSet;
        try {
            resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                this.allSubjectsDatas.add(new AllSubjectsData(
                        resultSet.getString(1),
                        resultSet.getString(2)));
            }
            for (int i = 0; i < this.allSubjectsDatas.size(); i++) {
                names.add(this.allSubjectsDatas.get(i).getSubject_Name());
            }
            return names;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
