package com.classroom.app.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

/**
 * Created by Muhammad Sadiq Saeed on 4/15/2017.
 */
@XmlRootElement
public class ChatRoom {

    private String chatRoomId;
    private String title;
    private String description;
    private Timestamp createdAt;
    private int groupType;
    private String groupAdmin;

    public ChatRoom() {
    }

    public ChatRoom(String chatRoomId, String title, String description, int groupType, String groupAdmin) {
        this.chatRoomId = chatRoomId;
        this.title = title;
        this.description = description;
        this.groupType = groupType;
        this.groupAdmin = groupAdmin;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(String groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatRoomId='" + chatRoomId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", groupType=" + groupType +
                ", groupAdmin='" + groupAdmin + '\'' +
                '}';
    }
}
