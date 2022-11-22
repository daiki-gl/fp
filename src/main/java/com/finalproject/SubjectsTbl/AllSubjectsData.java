package com.finalproject.SubjectsTbl;

import javafx.beans.property.*;

public class AllSubjectsData {
    private StringProperty subject_id;
    private StringProperty subject_name;

    public AllSubjectsData(String subject_id, String subject_name) {
        this.subject_id = new SimpleStringProperty(subject_id);
        this.subject_name = new SimpleStringProperty(subject_name);
    }

    public StringProperty subject_idProperty() {
        return subject_id;
    }

    public void setSubject_Id(StringProperty subject_id) {
        this.subject_id = subject_id;
    }

    public StringProperty subject_nameProperty() {
        return subject_name;
    }

    public void setSubject_Name(StringProperty subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_Id() {
        return subject_id.get();
    }

    public String getSubject_Name() {
        return subject_name.get();
    }
}
