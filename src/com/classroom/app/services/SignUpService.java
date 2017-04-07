package com.classroom.app.services;

import com.classroom.app.Interfaces.SignUpInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.SignIn;
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
    private String Message = null;
    private DBConnection dbConnection;
    private SignUpService signUpService;
    private KeyGenerationService keyGenerationService;
    private Statement statement;
    private ResultSet resultSet;
    private List<SignUp> userData;
    private String query;

    @Override
    public String createUser(String userName, String email, String password) {
        signUpService = new SignUpService();
        keyGenerationService = new KeyGenerationService();
        dbConnection = new DBConnection();
        String id = keyGenerationService.generateRandomString(15);

        EmailSendingService emailSendingService = new EmailSendingService();

        SignUp signUp = new SignUp(userName, email, password);
        try {
            con = dbConnection.openConnection();

            statement = con.createStatement();

            query = "Insert into signin_up (id, userName, email, password, status) Values ('" + id + "'," +
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
                statement.execute(query);
                Message = "Account created Successfully " + emailSendingService.sendMail(id, email, userName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.closeConnection(con);
        }


        return Message;
    }

    @Override
    public String checkIfUserExists(String email) {
        dbConnection = new DBConnection();
        try {
            con = dbConnection.openConnection();
            query = "Select email from signin_up where email = '" + email + "' ";
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                email = resultSet.getString("email");
                if (email != null) {
                    Message = "Account with this email already exists";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Message;
    }

    @Override
    public String checkUserName(String userName) {
        dbConnection = new DBConnection();
        try {
            con = dbConnection.openConnection();
            query = "Select userName from signin_up where userName = '" + userName + "'";
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                userName = resultSet.getString("userName");
                if (userName != null) {
                    Message = "Username already in use!!! ";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Message;
    }

    @Override
    public List<SignUp> getUserData(String userName) {
        dbConnection = new DBConnection();
        userData = new ArrayList<>();
        try {
            con = dbConnection.openConnection();
            query = "SELECT id, userName, email, password FROM signin_up WHERE userName = '" + userName + "'";
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                SignUp signUp = new SignUp();
                signUp.setId(resultSet.getString("id"));
                signUp.setUserName(resultSet.getString("userName"));
                signUp.setEmail(resultSet.getString("email"));
                signUp.setPassword(resultSet.getString("password"));
                userData.add(signUp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection(con);
        }

        return userData;
    }

    @Override
    public String updateUserData(String id, String userName, String password) {
        signUpService = new SignUpService();

        SignUp signUp = new SignUp();
        signUp.setId(id);
        signUp.setUserName(userName);
        signUp.setPassword(password);

        String checkMessage = null;
        dbConnection = new DBConnection();
        try {
            con = dbConnection.openConnection();
            statement = con.createStatement();

            query = "UPDATE signin_up SET userName = '" + signUp.getUserName() + "', password = '" + signUp.getPassword() + "' WHERE id = '" + signUp.getId() + "'";

            checkMessage = signUpService.checkUserName(userName);

            if (checkMessage != null) {
                Message = checkMessage;
            } else {
                statement.execute(query);
                Message = "Saved!!! ";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.closeConnection(con);
        }
        return Message;
    }

    @Override
    public String updateStatus(String id) {
        SignIn signIn = new SignIn();
        dbConnection = new DBConnection();
        try {
            con = dbConnection.openConnection();
            statement = con.createStatement();

            signIn.setStatus(checkStatus(id));
            System.out.println(signIn.getStatus());

            if (signIn.getStatus() == 0) {
                signIn.setStatus(1);
                query = "Update signin_up set status = '" + signIn.getStatus() + "' where id = '" + id + "'";
                statement.execute(query);
                Message = "Account Activated Successfully!!!! ";
            } else if (signIn.getStatus() == 1) {
                Message = "Your Account is already activated!!!! ";
            } else if (signIn.getStatus() == 2) {
                Message = "Your account is blocked and cannot be activated!!!! ";
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.closeConnection(con);
        }
        return Message;
    }

    @Override
    public int checkStatus(String id) {
        int status = 0;
        dbConnection = new DBConnection();
        try {
            con = dbConnection.openConnection();
            statement = con.createStatement();

            query = "Select status from signin_up where id= '" + id + "'";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                status = resultSet.getInt("status");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnection.closeConnection(con);
        }
        return status;
    }


}
