package com.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.finalproject.Signup.SignupModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignupController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirm;
    @FXML
    private Button signupBtn;
    @FXML
    private Hyperlink login;
    @FXML
    private Label signupStatus;

    SignupModel signupModel = new SignupModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (signupModel.isDatabaseConnected()) {
            // inject a text to dbStatus that db is connected
            System.out.println("Connected to Database");
        } else {
            System.out.println("Not Connected to Database");
        }
    }

    @FXML
    public void signup(ActionEvent event) {
        if (this.password.getText().equals(this.passwordConfirm.getText()) && !this.password.getText().equals("")
                && !this.username.getText().equals("")) {
            this.signupModel.isSignup(this.username.getText(), this.password.getText());
            Stage stage = (Stage) this.signupBtn.getScene().getWindow();
            stage.close();
            loginPage();
        } else {
            signupStatus.setText("Input proper username and password");
        }
    }

    @FXML
    public void BackToLogin(ActionEvent event) {
        Stage stage = (Stage) this.login.getScene().getWindow();
        stage.close();
        loginPage();
    }

    public void loginPage() {
        Stage signUpStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));

            signUpStage.setScene(scene);
            signUpStage.setTitle("Sign Up Page");
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
