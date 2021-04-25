package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MenuController {

    @FXML
    private Label withdrawLabel, balanceLabel;

    @FXML
    private TextField withdraw;

    @FXML
    private void CheckBalanceButtonOnAction(ActionEvent event) {
        float balance = AccountManagement.getInstance().getBalance();
        balanceLabel.setText(String.valueOf(balance));
    }

    @FXML
    private void WithdrawButtonOnAction(ActionEvent event) {
        Float amount = (Float.parseFloat(withdraw.getText()));
        if (AccountManagement.getInstance().withdraw((amount))){
            float balance = AccountManagement.getInstance().getBalance();
            balanceLabel.setText(String.valueOf(balance));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("withdrawal failed");
            alert.showAndWait();
        }
    }

}

