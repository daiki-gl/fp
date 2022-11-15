package com.finalproject;

import java.io.IOException;

import com.finalproject.Login.LoginModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
    LoginModel loginModel = new LoginModel();

    @FXML
    private Button teacherBtn;
    @FXML
    private Button subjectBtn;
    @FXML
    private Button adminBtn;

    // Subject
    @FXML
    public void switchToSubjectList(ActionEvent event) {
        Stage stage = (Stage) this.subjectBtn.getScene().getWindow();
        stage.close();
        subjectListPage();
    }

    public void subjectListPage() {
        Stage signUpStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SubjectList.fxml")));

            signUpStage.setScene(scene);
            signUpStage.setTitle("Subject List");
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Teacher
    @FXML
    public void switchToTeacherList(ActionEvent event) {
        Stage stage = (Stage) this.teacherBtn.getScene().getWindow();
        stage.close();
        teacherListPage();
    }

    public void teacherListPage() {
        Stage signUpStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("TeacherList.fxml")));

            signUpStage.setScene(scene);
            signUpStage.setTitle("Teacher List");
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // admin
    @FXML
    public void switchToAdminPage(ActionEvent event) {
        Stage stage = (Stage) this.adminBtn.getScene().getWindow();
        stage.close();
        adminPage();
    }

    public void adminPage() {
        Stage signUpStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Administrator.fxml")));

            signUpStage.setScene(scene);
            signUpStage.setTitle("Administrator");
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
