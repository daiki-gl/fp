package com.finalproject.TeacherList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.finalproject.dbUnit.dbConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

public class TeacherModel {

    Connection conn = null;
    private ObservableList<TeacherData> teacherData;

    public TeacherModel() {
        this.conn = dbConnection.getConnection();

        if (this.conn == null) {
            System.exit(0);
        }
    }

    // Show Teacher List(List Page)
    public ObservableList<TeacherData> getTeachers() {
        String query = "SELECT * FROM teachers_tbl ORDER BY teacher_id ASC";

        try {
            this.teacherData = FXCollections.observableArrayList();

            ResultSet resultSet = conn.createStatement().executeQuery(query);

            // id | createAt | name | department

            while (resultSet.next()) {
                this.teacherData.add(new TeacherData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }

            return teacherData;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search Teachers By Name
    public ObservableList<TeacherData> searchTeachersByName(String name) {
        String query = "SELECT * FROM teachers_tbl WHERE name LIKE ?;";

        try {
            this.teacherData = FXCollections.observableArrayList();

            PreparedStatement statement = null;
            ResultSet resultSet = null;
            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                this.teacherData.add(new TeacherData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
            return teacherData;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search Teachers By Date
    public ObservableList<TeacherData> searchTeachersByDate(DatePicker date) {
        String query = "SELECT * FROM teachers_tbl WHERE hire_date = ?;";

        try {
            this.teacherData = FXCollections.observableArrayList();

            PreparedStatement statement = null;
            ResultSet resultSet = null;
            statement = conn.prepareStatement(query);
            statement.setDate(1, Date.valueOf(date.getValue()));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                this.teacherData.add(new TeacherData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
            return teacherData;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search Teachers By Name
    public ObservableList<TeacherData> searchTeachersBySubject(String table) {
        String query = "SELECT * FROM teachers_tbl INNER JOIN " + table + "_tbl ON teachers_tbl.teacher_id = " + table
                + "_tbl.teacher_id;";
        // OR subject3;";

        try {
            this.teacherData = FXCollections.observableArrayList();

            PreparedStatement statement = null;
            ResultSet resultSet = null;
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                this.teacherData.add(new TeacherData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
            return teacherData;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Combo box making lists
    // public ObservableList<TeacherData> getTeacherNames() {
    ArrayList<String> names = new ArrayList<>();

    public ArrayList<String> getTeacherNames() {
        String query = "SELECT * FROM teachers_tbl";
        // this.teacherData = FXCollections.observableArrayList();

        ResultSet resultSet;

        try {
            resultSet = conn.createStatement().executeQuery(query);

            // Be able to Refactoring this code by using checkSubject method way
            while (resultSet.next()) {
                this.teacherData.add(new TeacherData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
            for (int i = 0; i < this.teacherData.size(); i++) {
                names.add(this.teacherData.get(i).getName());
            }
            // return this.teacherData.get(0).getName();
            return names;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Checking which subject was registered
    public int checkSubject(String subject) {
        String query = "SELECT subject_id FROM subjects_tbl WHERE subject_name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int subject_id = 0;

        if (subject == null) {
            subject = "Clear";
        }

        try {

            statement = conn.prepareStatement(query);
            statement.setString(1, subject);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                subject_id = resultSet.getInt(1);
            }
            return subject_id;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subject_id;
    }

    // Get every subjects tables' subject_id
    public int getEachSubjectId(String query) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int subject_id = 0;

        try {

            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                subject_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subject_id;
    }

    // get teacher_id to add this id to each subject table
    public int getTeacherIdAfterAdded() {
        int teacher_id = 0;
        String query = "SELECT teacher_id FROM teachers_tbl ORDER BY teacher_id DESC LIMIT 1";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                teacher_id = resultSet.getInt(1);
            }
            return teacher_id;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teacher_id;
    }

    public void addTeacher(String name, DatePicker hireDate, String password, String subject1, String subject2,
            String subject3) {
        String query = "INSERT INTO teachers_tbl (name, hire_date, password, subject1, subject2, subject3) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(query);
            LocalDate setHireDate = hireDate.getValue();

            statement.setString(1, name);
            statement.setDate(2, Date.valueOf(setHireDate));
            statement.setString(3, password);
            statement.setString(4, subject1);
            statement.setString(5, subject2);
            statement.setString(6, subject3);

            statement.execute();

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

    // Add English Table
    public void executeEachTable(int teacher_id, String query) {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, teacher_id);
            statement.execute();

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

    // public void deleteTeacher(int index) {
    public void deleteTeacher(String id) {
        String query = "DELETE FROM teachers_tbl WHERE teacher_id = ?";
        PreparedStatement statement = null;

        try {

            statement = conn.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));

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

    public ArrayList<String> getSubjectData(String id) {
        String query = "SELECT subject1, subject2, subject3 FROM teachers_tbl WHERE teacher_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> subjects = new ArrayList<>();

        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subjects.add(resultSet.getString(1));
                subjects.add(resultSet.getString(2));
                subjects.add(resultSet.getString(3));
            }
            return subjects;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subjects;
    }

    // =====================
    // UPDATE METHOD
    // Add hireDate column
    // =====================
    public void editTeacher(int index, String id, String name, Date hireDate, String password, String subject1,
            String subject2, String subject3) {
        String sql = "UPDATE teachers_tbl SET name = ?, hire_date = ? , password = ?, subject1 = ?, subject2 = ?, subject3 = ? WHERE teacher_id = ?";
        PreparedStatement statement = null;

        if (subject1 == null) {
            subject1 = teacherData.get(index).getSubject1();
        } else if (subject1.equals("Clear")) {
            subject1 = "";
        }
        if (subject2 == null) {
            subject2 = teacherData.get(index).getSubject2();
        } else if (subject2.equals("Clear")) {
            subject2 = "";
        }
        if (subject3 == null) {
            subject3 = teacherData.get(index).getSubject3();
        } else if (subject3.equals("Clear")) {
            subject3 = "";
        }

        // LocalDate setHireDate = hireDate.getValue();
        try {
            Connection conn = dbConnection.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            statement.setDate(2, hireDate);
            statement.setString(3, password);
            statement.setString(4, subject1);
            statement.setString(5, subject2);
            statement.setString(6, subject3);
            statement.setInt(7, Integer.parseInt(id));

            statement.execute();

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

    public ArrayList<String> getLogindata(String id) {
        ArrayList<String> loginData = new ArrayList<>();
        String query = "SELECT * FROM teachers_tbl WHERE teacher_id = ?;";
        // this.teacherData = FXCollections.observableArrayList();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("IDsql:" + id);
            statement = conn.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                this.teacherData.add(new TeacherData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
            loginData.add(this.teacherData.get(Integer.parseInt(id) - 1).getName());
            loginData.add(this.teacherData.get(Integer.parseInt(id) - 1).getHireDate());
            loginData.add(this.teacherData.get(Integer.parseInt(id) - 1).getSubject1());
            loginData.add(this.teacherData.get(Integer.parseInt(id) - 1).getSubject2());
            loginData.add(this.teacherData.get(Integer.parseInt(id) - 1).getSubject3());
            return loginData;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}