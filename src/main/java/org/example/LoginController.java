package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField username, password, showPassword;

    @FXML
    private void mouseEnter() {
        showPassword.setText(password.getText());
        showPassword.setVisible(true);
    }

    @FXML
    private void mouseExit() {
        showPassword.setVisible(false);
    }

    @FXML
    private void exitLoginButtonOnAction() {
        System.exit(0);
    }

    @FXML
    private void LogInButtonOnAction(ActionEvent event) {
        username.setText("Richard");
        password.setText("B0gg3");
        AccountManagement accountManagement = AccountManagement.getInstance();
        if (accountManagement.login(username.getText(), password.getText())) {
            SceneChanger.changeScene(event, "Menu.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password and Username does not match");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPassword.setVisible(false);
    }
}
