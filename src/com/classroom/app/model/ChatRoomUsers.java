package com.classroom.app.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Muhammad Sadiq Saeed on 4/17/2017.
 */
@XmlRootElement
public class ChatRoomUsers {

    private String userId;
    private String chatRoomId;

    public ChatRoomUsers() {
    }

    public ChatRoomUsers(String userId, String chatRoomId) {
        this.userId = userId;
        this.chatRoomId = chatRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    @Override
    public String toString() {
        return "ChatRoomUsers{" +
                "userId='" + userId + '\'' +
                ", chatRoomId='" + chatRoomId + '\'' +
                '}';
    }
}
