package com.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.finalproject.Login.LoginModel;

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

public class LoginController implements Initializable {
    LoginModel loginModel = new LoginModel();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Label loginStatus;
    @FXML
    private Hyperlink signup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (loginModel.isDatabaseConnected()) {
            // inject a text to dbStatus that db is connected
            System.out.println("Connected to Database");
        } else {
            System.out.println("Not Connected to Database");
        }

    }

    @FXML
    public void Login(ActionEvent event) {

        if (this.loginModel.isLogin(this.username.getText(), this.password.getText())) {
            Stage stage = (Stage) this.loginBtn.getScene().getWindow();
            stage.close();

            homePage();
        } else {
            this.loginStatus.setText("Wrong Credentials");
        }
    }

    // Register a new user

    public void homePage() {

        Stage homeStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));

            homeStage.setScene(scene);
            homeStage.setTitle("Home Page");
            homeStage.setResizable(false);
            homeStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Signup(ActionEvent event) {
        Stage stage = (Stage) this.signup.getScene().getWindow();
        stage.close();
        signUpPage();
    }

    public void signUpPage() {
        Stage signUpStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Signup.fxml")));

            signUpStage.setScene(scene);
            signUpStage.setTitle("Sign Up Page");
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
