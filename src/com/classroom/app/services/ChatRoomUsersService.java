package com.classroom.app.services;

import com.classroom.app.Interfaces.ChatRoomUsersInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.ChatRoomUsers;

import javax.xml.soap.SAAJResult;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Muhammad Sadiq Saeed on 4/17/2017.
 */
public class ChatRoomUsersService implements ChatRoomUsersInterface {

    private Connection connection = null;
    private Statement statement;
    private String query;
    private String message;
    private DBConnection dbConnection;


    @Override
    public String addUser(ChatRoomUsers chatRoomUsers) {
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Insert into chatroomusers(userId, chatRoomId) Values ('" + chatRoomUsers.getUserId() + "'," +
                    "'" + chatRoomUsers.getChatRoomId() + "')";

            statement.execute(query);

            message = "User ";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public String removeUser(ChatRoomUsers chatRoomUsers) {
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Delete from chatroomusers where userId = '" + chatRoomUsers.getUserId() + "' And " +
                    "chatRoomId = '" + chatRoomUsers.getChatRoomId() + "'";

            statement.execute(query);
            message = "User removed from the group";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                dbConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
