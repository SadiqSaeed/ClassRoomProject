package com.classroom.app.Interfaces;

import java.sql.Timestamp;

/**
 * Created by Muhammad Sadiq Saeed on 4/14/2017.
 */
public interface MessageInterface {

    void sendMessage(String message, String author, String chatId);

}
