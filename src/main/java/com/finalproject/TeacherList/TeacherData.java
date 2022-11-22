package com.finalproject.TeacherList;

import javafx.beans.property.*;

public class TeacherData {

    private StringProperty id;
    private StringProperty name;
    private StringProperty hireDate; // Date is not String
    private StringProperty password;
    private StringProperty subject1;
    private StringProperty subject2;
    private StringProperty subject3;

    public TeacherData(String id, String name, String hireDate, String password, String subject1, String subject2,
            String subject3) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.hireDate = new SimpleStringProperty(hireDate);
        this.password = new SimpleStringProperty(password);
        this.subject1 = new SimpleStringProperty(subject1);
        this.subject2 = new SimpleStringProperty(subject2);
        this.subject3 = new SimpleStringProperty(subject3);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(StringProperty id) {
        this.id = id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public StringProperty hireDateProperty() {
        return hireDate;
    }

    public String getHireDate() {
        return hireDate.get();
    }

    public void setHireDate(StringProperty hireDate) {
        this.hireDate = hireDate;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

    public StringProperty subject1Property() {
        return subject1;
    }

    public String getSubject1() {
        return subject1.get();
    }

    public void setSubject1(StringProperty subject1) {
        this.subject1 = subject1;
    }

    public StringProperty subject2Property() {
        return subject2;
    }

    public String getSubject2() {
        return subject2.get();
    }

    public void setSubject2(StringProperty subject2) {
        this.subject2 = subject2;
    }

    public StringProperty subject3Property() {
        return subject3;
    }

    public String getSubject3() {
        return subject3.get();
    }

    public void setSubject3(StringProperty subject3) {
        this.subject3 = subject3;
    }
}
