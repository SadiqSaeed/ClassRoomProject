package com.classroom.app.webservices;

import com.classroom.app.Interfaces.ChatRoomUsersInterface;
import com.classroom.app.model.ChatRoomUsers;
import com.classroom.app.services.ChatRoomUsersService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Muhammad Sadiq Saeed on 5/5/2017.
 */
@Path("/ChatRoomUsers")
@Consumes(MediaType.APPLICATION_JSON)
public class ChatRoomUsersResource {

    private ChatRoomUsersInterface chatRoomUsersInterface;
    private ChatRoomUsers chatRoomUsers;

    @POST
    @Path("/{chatRoomId}/{userId}")
    public Response addUsers(@PathParam("chatRoomId") String chatRoomId, @PathParam("userId") String userId) {
        //Creating Reference
        chatRoomUsersInterface = new ChatRoomUsersService();
        chatRoomUsers = new ChatRoomUsers();

        //Setting Values
        chatRoomUsers.setChatRoomId(chatRoomId);
        chatRoomUsers.setUserId(userId);

        //Returning Response
        return Response.status(200)
                .entity("Message: " + chatRoomUsersInterface.addUser(chatRoomUsers))
                .build();
    }

    @DELETE
    @Path("/{chatRoomId}/{userId}")
    public Response removeUsers(@PathParam("chatRoomId") String chatRoomId, @PathParam("userId") String userId) {
        //Creating Reference
        chatRoomUsersInterface = new ChatRoomUsersService();
        chatRoomUsers = new ChatRoomUsers();

        //Setting Values
        chatRoomUsers.setChatRoomId(chatRoomId);
        chatRoomUsers.setUserId(userId);

        //Returning Response
        return Response.status(200)
                .entity("Message: " + chatRoomUsersInterface.removeUser(chatRoomUsers))
                .build();
    }
}
