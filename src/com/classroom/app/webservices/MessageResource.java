package com.classroom.app.webservices;

import com.classroom.app.Interfaces.MessageInterface;
import com.classroom.app.model.Message;
import com.classroom.app.services.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 5/5/2017.
 */
@Path("/Messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageInterface messageInterface;
    private Message messageClass;

    @GET
    public List<Message> getAllMessages(@QueryParam("chatId") String chatId) {
        //Creating References
        messageInterface = new MessageService();
        //Returning messageList
        return messageInterface.getAllMessages(chatId);
    }

    @GET
    public List<Message> getMessagesForYear(@QueryParam("year") int year) {
        //Creating References
        messageInterface = new MessageService();
        //Returning messageList
        return messageInterface.getMessagesforYear(year);
    }

    @GET
    @Path("/{start}/{size}")
    public List<Message> getMessagePaginated(@PathParam("start") int start, @PathParam("size") int size) {
        //Creating References
        messageInterface = new MessageService();
        //Returning messageList
        return messageInterface.getMessagePaginated(start, size);
    }

    @POST
    @Path("/{message}/{author}/{chatId}")
    public void sendMessage(@PathParam("message") String message, @PathParam("author") String author, @PathParam("chatId") String chatId) {
        //Creating References
        messageInterface = new MessageService();
        messageClass = new Message();

        //Setting Values
        messageClass.setMessage(message);
        messageClass.setAuthor(author);
        messageClass.setChatId(chatId);

        //Calling sendMessage method
        messageInterface.sendMessage(messageClass);
    }


    @DELETE
    public void removeMessage(@QueryParam("messageId") int messageId) {
        //Creating References
        messageInterface = new MessageService();

        //Calling removeMessage method
        messageInterface.removeMessage(messageId);
    }

}
