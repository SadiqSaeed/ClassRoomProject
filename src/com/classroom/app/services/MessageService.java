package com.classroom.app.services;

import com.classroom.app.Interfaces.MessageInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 4/14/2017.
 */
public class MessageService implements MessageInterface {

    private Connection connection = null;
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
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

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
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void removeMessage(int messageId) {
        dbConnection = new DBConnection();
        messageClass = new Message();
        messageClass.setMessageID(messageId);
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Delete from messages where messageId = '" + messageClass.getMessageID() + "'";

            statement.execute(query);
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

    }

    @Override
    public List<Message> getAllMessages(String chatId) {
        dbConnection = new DBConnection();
        messageList = new ArrayList<>();
        messageClass = new Message();
        messageClass.setChatId(chatId);
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Select * from messages where chatId = '" + messageClass.getChatId() + "'";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                messageClass = new Message();
                messageClass.setMessageID(resultSet.getInt("messageId"));
                messageClass.setMessage(resultSet.getString("message"));
                messageClass.setCreatedAt(resultSet.getTimestamp("createdAt"));
                messageClass.setAuthor(resultSet.getString("author"));
                messageClass.setChatId(resultSet.getString("chatId"));
                messageList.add(messageClass);
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
        return messageList;
    }

    @Override
    public List<Message> getMessagePaginated(int start, int size, String chatId) {
        return null;
    }
}
