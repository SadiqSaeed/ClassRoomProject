package com.classroom.app.Interfaces;

import com.classroom.app.model.Message;
import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 4/14/2017.
 */
public interface MessageInterface {

    void sendMessage(String message, String author, String chatId);

    void removeMessage(int messageId);

    List<Message> getAllMessages(String chatId);

    List<Message> getMessagePaginated(int start, int size, String chatId);

}
