package com.classroom.app.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

/**
 * Created by Muhammad Sadiq Saeed on 4/14/2017.
 */
@XmlRootElement
public class Message {

    private int messageID;
    private String message;
    private Timestamp createdAt;
    private String author;
    private String chatId;

    public Message() {

    }

    public Message(String message, String author, String chatId) {
        this.message = message;
        this.author = author;
        this.chatId = chatId;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", author='" + author + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}
