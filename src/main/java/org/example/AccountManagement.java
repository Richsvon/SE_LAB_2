package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountManagement {
    String username;
    String password;
    private float balance;
    private DbConnect dbc = new DbConnect();
    private static AccountManagement single_instance = null;

    public static AccountManagement getInstance() {
        if (single_instance == null) {
            single_instance = new AccountManagement();
        }
        return single_instance;
    }

    private AccountManagement() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        try {
            Statement stmt = dbc.getConnection().createStatement();
            stmt.executeQuery("use lab2se1");
            ResultSet rs = stmt.executeQuery("select balance from account where account.userAccount = '" + username + "' and account.password = '" + password + "'");
            rs.next();
            balance = rs.getFloat(1);
            return balance;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return balance;
        }
    }

    public boolean withdraw(float withdraw) {
        if (withdraw > this.balance) {
            return false;
        } else {
            try {
                Statement stmt = dbc.getConnection().createStatement();
                stmt.executeQuery("use lab2se1");
                stmt.executeUpdate("update account set balance = " + (balance-withdraw) + " where account.userAccount = '" + username + "' and account.password = '" + password + "'");
                this.balance = getBalance();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }

            return true;
        }
    }

    public boolean login(String username, String password) {
        try {
            Statement stmt = dbc.getConnection().createStatement();
            stmt.executeQuery("use lab2se1");
            ResultSet rs = stmt.executeQuery("select balance from account where account.userAccount = '" + username + "' and account.password = '" + password + "'");
            if (rs.next()) {
                setUsername(username);
                setPassword(password);
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
