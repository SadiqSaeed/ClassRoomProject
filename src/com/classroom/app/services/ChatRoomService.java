package com.classroom.app.services;

import com.classroom.app.Interfaces.ChatRoomInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.ChatRoom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 4/15/2017.
 */
public class ChatRoomService implements ChatRoomInterface {

    private Connection connection = null;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    private DBConnection dbConnection;
    private String message;
    private List<ChatRoom> chatRoomList;


    @Override
    public String createSession(ChatRoom chatRoom) {
        dbConnection = new DBConnection();
        String id = KeyGenerationService.generateRandomString(10);
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Insert into chatroom(chatRoomId, title, description, groupType, groupAdmin) Values('" + id + "'," +
                    "'" + chatRoom.getTitle() + "', '" + chatRoom.getDescription() + "', '" + chatRoom.getGroupType() + "', '" + chatRoom.getGroupAdmin() + "')";

            if (!(chatRoom.getTitle().equals("") && chatRoom.getDescription().equals(""))) {
                statement.execute(query);
                message = "Session Created!!!! ";
            } else {
                if (chatRoom.getTitle().equals("")) {
                    message = "Title cannot be empty!!!! ";
                } else {
                    message = "Description cannot be empty!!!! ";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
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

    @Override
    public String updateSessionInfo(ChatRoom chatRoom) {
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Update chatroom set title = '" + chatRoom.getTitle() + "', description = '" + chatRoom.getDescription() + "' " +
                    "where chatRoomId = '" + chatRoom.getChatRoomId() + "'";

            if (!(chatRoom.getTitle().equals("") && chatRoom.getDescription().equals(""))) {
                statement.execute(query);
                message = "Updated!!!! ";
            } else {
                if (chatRoom.getTitle().equals("")) {
                    message = "Title cannot be empty!!!! ";
                } else {
                    message = "Description cannot be empty!!!! ";
                }
            }

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

    @Override
    public List<ChatRoom> getChatRoomInfo(String chatRoomId) {
        chatRoomList = new ArrayList<>();
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Select * from chatroom where chatRoomId = '" + chatRoomId + "' ";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.setChatRoomId(resultSet.getString("chatRoomId"));
                chatRoom.setTitle(resultSet.getString("title"));
                chatRoom.setDescription(resultSet.getString("description"));
                chatRoom.setCreatedAt(resultSet.getTimestamp("createdAt"));
                chatRoom.setGroupType(resultSet.getInt("groupType"));
                chatRoom.setGroupAdmin(resultSet.getString("groupAdmin"));
                chatRoomList.add(chatRoom);
            }

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
        return chatRoomList;
    }

    @Override
    public List<ChatRoom> getAllChatRoomsInfoForUser(String userId) {
        chatRoomList = new ArrayList<>();
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Select * from chatroom  in (SELECT chatRoomId where userID = '" + userId + "')";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.setChatRoomId(resultSet.getString("chatRoomId"));
                chatRoom.setTitle(resultSet.getString("title"));
                chatRoom.setDescription(resultSet.getString("description"));
                chatRoom.setCreatedAt(resultSet.getTimestamp("createdAt"));
                chatRoom.setGroupType(resultSet.getInt("groupType"));
                chatRoom.setGroupAdmin(resultSet.getString("groupAdmin"));
                chatRoomList.add(chatRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection(connection);
        }
        return chatRoomList;
    }

    @Override
    public void deleteSession(String chatRoomId) {
        dbConnection = new DBConnection();
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "delete from chatRoom where chatRoomId='" + chatRoomId + "'";

            statement.equals(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection(connection);
        }

    }


}
