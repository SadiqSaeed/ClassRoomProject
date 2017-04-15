package com.classroom.app.services;

import com.classroom.app.Interfaces.MessageInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.Message;

import java.sql.*;
import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 4/14/2017.
 */
public class MessageService implements MessageInterface {

    private Connection con = null;
    private DBConnection dbConnection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    private Message messageClass;
    private List<Message> messageList;

    @Override
    public void sendMessage(String message, String author, String chatId) {
        dbConnection = new DBConnection();
        messageClass = new Message(message, author, chatId);
        try {
            con = dbConnection.openConnection();
            statement = con.createStatement();

            query = "Insert into messages(message, author, chatId) Values ( '" + messageClass.getMessage() + "', " +
                    "'" + messageClass.getAuthor() + "', '" + messageClass.getChatId() + "')";

            if (!(messageClass.getMessage().equals(""))) {
                statement.execute(query);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
