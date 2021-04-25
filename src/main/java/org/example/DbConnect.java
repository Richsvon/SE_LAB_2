package org.example;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DbConnect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://den1.mysql1.gear.host", "lab2se1", "Vi98?n9LS-4m");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to connect to the database");
            alert.showAndWait();
        }
    }
}
