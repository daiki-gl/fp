package com.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.finalproject.Login.AdminLoginModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController implements Initializable {
    AdminLoginModel adminLoginModel = new AdminLoginModel();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Label loginStatus;
    @FXML
    private Hyperlink login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (adminLoginModel.isDatabaseConnected()) {
            // inject a text to dbStatus that db is connected
            System.out.println("Connected to Database");
        } else {
            System.out.println("Not Connected to Database");
        }

    }

    @FXML
    public void Login(ActionEvent event) {

        if (this.adminLoginModel.isLogin(this.username.getText(), this.password.getText())) {
            Stage stage = (Stage) this.loginBtn.getScene().getWindow();
            stage.close();
            homePage();
        } else {
            this.loginStatus.setText("Wrong Credentials");
        }
    }

    public void homePage() {
        Stage homeStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AdminTeacherList.fxml")));

            homeStage.setScene(scene);
            homeStage.setTitle("Admin: Teacher List Page");
            homeStage.setResizable(false);
            homeStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void BackToLogin(ActionEvent event) {
        Stage stage = (Stage) this.login.getScene().getWindow();
        stage.close();
        loginPage();
    }

    public void loginPage() {
        Stage loginStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));

            loginStage.setScene(scene);
            loginStage.setTitle("Login Page");
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
