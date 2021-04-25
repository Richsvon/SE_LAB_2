package org.example;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountManagementTest {

    @Test
    public void getBalance() {
        float expectedBalance = 0;
        float actualBalance;
        try {
            DbConnect dbc = new DbConnect();
            Statement stmt = dbc.getConnection().createStatement();
            stmt.executeQuery("use lab2se1");
            ResultSet rs = stmt.executeQuery("select balance from account where account.userAccount = 'Richard' and account.password = 'B0gg3'");
            rs.next();
            expectedBalance = rs.getFloat(1);
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

        AccountManagement accountManagement = AccountManagement.getInstance();
        accountManagement.setUsername("Richard");
        accountManagement.setPassword("B0gg3");
        actualBalance = accountManagement.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance, 0.00);
    }

    @Test
    public void withdraw() {
        float expectedBalance = 0;
        float actualBalance;

        try {
            DbConnect dbc = new DbConnect();
            Statement stmt = dbc.getConnection().createStatement();
            stmt.executeQuery("use lab2se1");
            ResultSet rs = stmt.executeQuery("select balance from account where account.userAccount = 'Richard' and account.password = 'B0gg3'");
            rs.next();
            expectedBalance = rs.getFloat(1);
            expectedBalance = expectedBalance - 40;
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

        AccountManagement accountManagement = AccountManagement.getInstance();
        accountManagement.setUsername("Richard");
        accountManagement.setPassword("B0gg3");
        accountManagement.getBalance();
        accountManagement.withdraw(40);
        actualBalance = accountManagement.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance, 0.00);
    }

    @Test
    public void login() {
        AccountManagement accountManagement = AccountManagement.getInstance();
        boolean actual = accountManagement.login("Richard", "B0gg3");
        Assert.assertTrue(actual);
    }

    @Test
    public void setUsername() {
        String expected = "Richard";
        String actual;
        AccountManagement accountManagement = AccountManagement.getInstance();
        accountManagement.setUsername("Richard");
        actual = accountManagement.username;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setPassword() {
        String expected = "B0gg3";
        String actual;
        AccountManagement accountManagement = AccountManagement.getInstance();
        accountManagement.setPassword("B0gg3");
        actual = accountManagement.password;
        Assert.assertEquals(expected,actual);
    }
}