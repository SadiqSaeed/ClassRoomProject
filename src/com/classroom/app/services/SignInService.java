package com.classroom.app.services;

import com.classroom.app.Interfaces.SignInInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.SignIn;

import javax.xml.bind.annotation.XmlRootElement;
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
    public String authenticateUser(String userName_email, String password) {
        dbConnection = new DBConnection();
        signIn = new SignIn(userName_email, password);
        try {
            con = dbConnection.openConnection();
            statement = con.createStatement();

            String query = "Select * from signin_up where userName = '" + signIn.getUserName_email() + "' And password = '" + signIn.getPassword() + "'" +
                    " OR email = '" + signIn.getUserName_email() + "' And password = '" + signIn.getPassword() + "'";

            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                signIn.setStatus(rs.getInt("status"));
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
