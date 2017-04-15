package com.classroom.app.Interfaces;


import com.classroom.app.model.ChatRoom;

import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 4/15/2017.
 */
public interface ChatRoomInterface {

    String createSession(String title, String description, int groupType, String groupAdmin);

    String updateSession(String title, String description, String chatRoomId);

    List<ChatRoom> getChatRoomInfo(String chatRoomId);

    List<ChatRoom> getAllChatRooms(String userId);

    void addChatRoomUser();
}
