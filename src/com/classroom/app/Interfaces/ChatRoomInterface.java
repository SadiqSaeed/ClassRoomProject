package com.classroom.app.Interfaces;


import com.classroom.app.model.ChatRoom;

import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 4/15/2017.
 */
public interface ChatRoomInterface {

    String createSession(ChatRoom chatRoom);

    String updateSessionInfo(ChatRoom chatRoom);

    List<ChatRoom> getChatRoomInfo(String chatRoomId);

    List<ChatRoom> getAllChatRoomsInfoForUser(String userId);

    void deleteSession(String chatRoomId);
}
