package com.classroom.app.services;

import com.classroom.app.Interfaces.ChatRoomInterface;
import com.classroom.app.database.DBConnection;
import com.classroom.app.model.ChatRoom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Muhammad Sadiq Saeed on 4/15/2017.
 */
public class ChatRoomService implements ChatRoomInterface {

    private Connection connection = null;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    private DBConnection dbConnection;
    private ChatRoom chatRoom;
    private String message;


    @Override
    public String createSession(String title, String description, int groupType, String groupAdmin) {
        dbConnection = new DBConnection();
        chatRoom = new ChatRoom(KeyGenerationService.generateRandomString(10), title, description, groupType, groupAdmin);
        try {
            connection = dbConnection.openConnection();
            statement = connection.createStatement();

            query = "Insert into chatroom(chatRoomId, title, description, groupType, groupAdmin) Values('" + chatRoom.getChatRoomId() + "'," +
                    "'" + chatRoom.getTitle() + "', '" + chatRoom.getDescription() + "', '" + chatRoom.getGroupType() + "', '" + chatRoom.getGroupAdmin() + "')";

            if (!(chatRoom.getTitle().equals("") && chatRoom.getDescription().equals(""))) {
                statement.execute(query);
                message = "Session Created!!!! ";
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
}
