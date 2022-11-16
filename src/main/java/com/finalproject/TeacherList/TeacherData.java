package com.finalproject.TeacherList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeacherData {

    private StringProperty id;
    private StringProperty name;
    private StringProperty hireDate; // Date is not String
    private StringProperty password;

    // Add hireDate values to everything (getter, setter, constructor)
    public TeacherData(String id, String name, String hireDate, String password) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.hireDate = new SimpleStringProperty(hireDate);
        this.password = new SimpleStringProperty(password);
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
        return hireDate;
    }

    public String getPassword() {
        return hireDate.get();
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

}
