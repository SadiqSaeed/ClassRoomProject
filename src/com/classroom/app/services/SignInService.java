package com.classroom.app.services;

import com.classroom.app.Interfaces.SignInInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.SignIn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignInService implements SignInInterface {

    private Connection con = null;
    private String Message = "";
    private SignIn signIn;
    private DBConnection dbConnection;
    private Statement statement;

    @Override
    public String authenticateUser(String email, String password) {
        dbConnection = new DBConnection();
        try {
            con = dbConnection.openConnection();
            statement = con.createStatement();

            String query = "Select email,password,status from users where email = '" + signIn.getEmail() + "' And " +
                    "password = '" + signIn.getPassword() + "'";

            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                signIn.setEmail(rs.getString("email"));
                signIn.setPassword(rs.getString("password"));
                signIn.setStatus(rs.getInt("status"));

                if (signIn.getEmail().equals(email) && signIn.getPassword().equals(password)) {
                    if (signIn.getStatus() == 0) {
                        Message = "Your registration is not complete yet please check your mail";
                    } else if (signIn.getStatus() == 1) {
                        Message = "SuccessFull LogIn";
                    } else if (signIn.getStatus() == 2) {
                        Message = "Account with this email is blocked";
                    }
                } else {
                    Message = "Invalid email or password";
                }
            } else {
                Message = "Invalid email or password";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                dbConnection.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Message;
    }

}
