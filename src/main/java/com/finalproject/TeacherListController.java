package com.finalproject;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import com.finalproject.TeacherList.TeacherData;
import com.finalproject.TeacherList.TeacherModel;

// import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TeacherListController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private DatePicker hireDate; // change to PasswordField later
    // private TextField hireDate; // change to PasswordField later

    // Add hireDate column
    @FXML
    private TextField password;

    @FXML
    private TableView<TeacherData> teacherDataTableView;
    @FXML
    private TableColumn<TeacherData, String> idColumn;
    @FXML
    private TableColumn<TeacherData, String> nameColumn;
    @FXML
    private TableColumn<TeacherData, String> passwordColumn;
    @FXML
    private TableColumn<TeacherData, String> hireDateColumn;

    @FXML
    private Button addTeacherBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button deleteTeacherBtn;
    @FXML
    private Button updateTeacherBtn;
    @FXML
    private Label errorMsg;

    public TableView<TeacherData> getTeacherDataTableView() {
        return teacherDataTableView;
    }

    // instantiate a model
    TeacherModel teacherModel = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.teacherModel = new TeacherModel();
        this.loadTeacherData();
    }

    // load data
    @FXML
    public void loadTeacherData() {

        this.idColumn.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("name"));
        // this.passwordColumn.setCellValueFactory(new PropertyValueFactory<TeacherData,
        // String>("password"));
        this.hireDateColumn.setCellValueFactory(new PropertyValueFactory<TeacherData, String>("hireDate"));

        this.teacherDataTableView.setItems(teacherModel.getTeachers());
        errorMsg.setText(null);
    }

    // add employee
    @FXML
    private void addTeacher(ActionEvent event) {
        if (!this.name.getText().equals("") && !this.password.getText().equals("")) {
            teacherModel.addTeacher(this.name.getText(), this.hireDate, this.password.getText());
            this.loadTeacherData();
            this.clearFields(null);
        } else {
            errorMsg.setText("*Input name and department");
        }
    }

    // update employee
    // @FXML
    // private void updateEmployee(ActionEvent event) {
    // if (teacherDataTableView.getSelectionModel().getSelectedIndex() != -1) {
    // teacherModel.updateEmployee(teacherDataTableView.getSelectionModel().getSelectedIndex(),
    // this.name.getText(),
    // this.department.getText());
    // this.loadTeacherData();
    // this.clearFields(null);
    // } else {
    // errorMsg.setText("*Select a row which you want to update");
    // }
    // }

    // delete Teacher
    @FXML
    private void deleteTeacher(ActionEvent event) {
        if (teacherDataTableView.getSelectionModel().getSelectedIndex() != -1) {
            teacherModel.deleteTeacher(teacherDataTableView.getSelectionModel().getSelectedIndex());
            this.loadTeacherData();
            this.clearFields(null);
        } else {
            errorMsg.setText("*Select employee data which you want to delete");
        }
    }

    // clear fields
    @FXML
    private void clearFields(ActionEvent event) {
        this.name.setText("");
        this.password.setText("");
        errorMsg.setText(null);
    }
}
