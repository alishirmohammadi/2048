package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import models.User;
import views.Main;

import java.io.IOException;

public class LoginController {
    @FXML Label message;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        try {
            User user = new User(usernameField.getText(), passwordField.getText());
            message.getStyleClass().remove("warning");
            message.getStyleClass().add("success");
            message.setText("Account created.");
            usernameField.setText("");
            passwordField.setText("");
        } catch (User.UserExistsException exception) {
            message.setText("Account with this username exists.");
            message.getStyleClass().remove("success");
            message.getStyleClass().add("warning");
            usernameField.getStyleClass().add("wrong");
            passwordField.getStyleClass().remove("wrong");
        }
    }

    public void handleLoginButtonAction(ActionEvent actionEvent) {
        try {
            User user = User.authenticateUser(usernameField.getText(), passwordField.getText());
            Main.showMenu();
            Main.setLoggedInUser(user);
        } catch (User.UserNotFoundException e) {
            message.setText("Account with this username not found.");
            message.getStyleClass().remove("success");
            message.getStyleClass().add("warning");
            usernameField.getStyleClass().add("wrong");
        } catch (User.WrongPasswordException e) {
            message.setText("Wrong password!");
            message.getStyleClass().remove("success");
            message.getStyleClass().add("warning");
            usernameField.getStyleClass().remove("wrong");
            passwordField.getStyleClass().add("wrong");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleScoreboardButtonAction(ActionEvent actionEvent) {

    }

    public void handleUsernameFieldAction(KeyEvent keyEvent) {
        usernameField.getStyleClass().remove("wrong");
    }

    public void handlePasswordFieldAction(KeyEvent keyEvent) {
        passwordField.getStylesheets().remove("wrong");
    }
}
