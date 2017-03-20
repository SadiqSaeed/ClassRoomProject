package com.classroom.app.services;

import com.classroom.app.Interfaces.SignUpInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.SignUp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 3/2/2017.
 */
public class SignUpService implements SignUpInterface {

    private Connection con = null;
    private String id;
    private String Message = null;
    private DBConnection dbConnection = new DBConnection();

    @Override
    public String createUser(String userName, String email, String password) {
        SignUpService signUpService = new SignUpService();
        KeyGenerationService keyGenerationService = new KeyGenerationService();
        id = keyGenerationService.generateRandomString(15);

        EmailSendingService emailSendingService = new EmailSendingService();

        SignUp signUp = new SignUp(userName, email, password);
        try {
            con = dbConnection.openConnection();

            Statement statement = con.createStatement();

            String Query = "Insert into logging (id, userName, email, password, status) Values ('" + id + "'," +
                    "'" + signUp.getUserName() + "', '" + signUp.getEmail() + "', '" + signUp.getPassword() + "', '" + signUp.getStatus() + "')";

            String checkMessage1 = null;
            String checkMessage2 = null;

            checkMessage1 = signUpService.checkIfUserExists(email);
            checkMessage2 = signUpService.checkUserName(userName);

            if (checkMessage1 != null) {
                Message = checkMessage1;
            } else if (checkMessage2 != null) {
                Message = checkMessage2;
            } else {
                statement.execute(Query);
                Message = "Account created Successfully " + emailSendingService.sendMail(email,userName);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            dbConnection.closeConnection(con);
        }


        return Message;
    }

    @Override
    public String checkIfUserExists(String email) {
        try {
            con = dbConnection.openConnection();
            String query = "Select email from logging where email = '" + email + "' ";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                email = resultSet.getString("email");
                if (email != null) {
                    Message = "Account with this email already exists";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Message;
    }

    @Override
    public String checkUserName(String userName) {
        try {
            con = dbConnection.openConnection();
            String query = "Select userName from logging where userName = '" + userName + "'";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                userName = resultSet.getString("userName");
                if (userName != null) {
                    Message = "Username already in use!!! ";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Message;
    }

    @Override
    public List<SignUp> getUserData(String userName) {
        List<SignUp> userData = new ArrayList<>();
        try {
            con = dbConnection.openConnection();
            String query = "SELECT id, userName, email, password FROM logging WHERE userName = '" + userName + "'";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                SignUp signUp = new SignUp();
                signUp.setId(resultSet.getString("id"));
                signUp.setUserName(resultSet.getString("userName"));
                signUp.setEmail(resultSet.getString("email"));
                signUp.setPassword(resultSet.getString("password"));
                userData.add(signUp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            dbConnection.closeConnection(con);
        }

        return userData;
    }

    @Override
    public String updateUserData(String id, String userName, String password) {
        SignUpService signUpService = new SignUpService();

        SignUp signUp = new SignUp();
        signUp.setId(id);
        signUp.setUserName(userName);
        signUp.setPassword(password);

        String checkMessage = null;

        try {
            con = dbConnection.openConnection();
            Statement statement = con.createStatement();

            String query = "UPDATE logging SET userName = '" + signUp.getUserName() + "', password = '" + signUp.getPassword() + "' WHERE id = '" + signUp.getId() + "'";

            checkMessage = signUpService.checkUserName(userName);

            if (checkMessage != null) {
                Message = checkMessage;
            } else {
                statement.execute(query);
                Message = "Saved!!! ";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            dbConnection.closeConnection(con);
        }
        return Message;
    }
}
