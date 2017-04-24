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

    private Connection connection = null;
    private String Message = null;
    private DBConnection dbConnection;
    private SignUpService signUpService;
    private EmailSendingService emailSendingService;
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

        emailSendingService = new EmailSendingService();

        SignUp signUp = new SignUp(userName, email, password);
        try {
            connection = dbConnection.openConnection();

            statement = connection.createStatement();

            query = "Insert into users (userId, userName, email, password, status) Values ('" + id + "'," +
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
                Message = "Account created Successfully "; //+ emailSendingService.sendMail(id, email, userName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Message;
    }

    @Override
    public String checkIfUserExists(String email) {
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            query = "Select email from users where email = '" + email + "' ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                email = resultSet.getString("email");
                if (email != null) {
                    Message = "Account with this email already exists";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Message;
    }

    @Override
    public String checkUserName(String userName) {
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            query = "Select userName from users where userName = '" + userName + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                userName = resultSet.getString("userName");
                if (userName != null) {
                    Message = "Username already in use!!! ";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Message;
    }

    @Override
    public List<SignUp> getUserData(String userName) {
        dbConnection = new DBConnection();
        userData = new ArrayList<>();
        try {
            connection = dbConnection.openConnection();
            query = "SELECT userId, userName, email, password FROM users WHERE userName = '" + userName + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                SignUp signUp = new SignUp();
                signUp.setId(resultSet.getString("userId"));
                signUp.setUserName(resultSet.getString("userName"));
                signUp.setEmail(resultSet.getString("email"));
                signUp.setPassword(resultSet.getString("password"));
                userData.add(signUp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "UPDATE users SET userName = '" + signUp.getUserName() + "', password = '" + signUp.getPassword() + "' WHERE userId = '" + signUp.getId() + "'";

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
            try {
                statement.close();
                resultSet.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Message;
    }

    @Override
    public String updateStatus(String id) {
        SignIn signIn = new SignIn();
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            signIn.setStatus(checkStatus(id));
            System.out.println(signIn.getStatus());

            if (signIn.getStatus() == 0) {
                signIn.setStatus(1);
                query = "Update users set status = '" + signIn.getStatus() + "' where userId = '" + id + "'";
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
            try {
                statement.close();
                resultSet.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Message;
    }

    @Override
    public int checkStatus(String id) {
        int status = 0;
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Select status from users where userId= '" + id + "'";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                status = resultSet.getInt("status");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }


}
