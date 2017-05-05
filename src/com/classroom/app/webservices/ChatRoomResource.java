package com.classroom.app.webservices;

import com.classroom.app.Interfaces.ChatRoomInterface;
import com.classroom.app.model.ChatRoom;
import com.classroom.app.services.ChatRoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 4/15/2017.
 */
@Path("/ChatRoom")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatRoomResource {

    private ChatRoomInterface chatRoomInterface;
    private ChatRoom chatRoom;

    @POST
    @Path("/{title}/{description}/{groupType}/{groupAdmin}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createSession(@PathParam("title") String title, @PathParam("description") String description,
                                  @PathParam("groupType") int groupType, @PathParam("groupAdmin") String groupAdmin) {
        //Creating Reference
        chatRoomInterface = new ChatRoomService();
        chatRoom = new ChatRoom();

        //Setting Values
        chatRoom.setTitle(title);
        chatRoom.setDescription(description);
        chatRoom.setGroupType(groupType);
        chatRoom.setGroupAdmin(groupAdmin);

        //Returning Response
        return Response.status(200)
                .entity(chatRoomInterface.createSession(chatRoom))
                .build();
    }

    @PUT
    @Path("/{title}/{description}/{chatRoomId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSession(@PathParam("title") String title, @PathParam("description") String description,
                                  @PathParam("chatRoomId") String chatRoomId) {
        //Creating Reference
        chatRoomInterface = new ChatRoomService();
        chatRoom = new ChatRoom();

        //Setting Values
        chatRoom.setTitle(title);
        chatRoom.setDescription(description);
        chatRoom.setChatRoomId(chatRoomId);

        //Returning Response
        return Response.status(200)
                .entity(chatRoomInterface.updateSessionInfo(chatRoom))
                .build();
    }

    @GET
    public List<ChatRoom> getChatRoomInfo(@QueryParam("chatRoomId") String chatRoomId) {
        //Creating Reference
        chatRoomInterface = new ChatRoomService();

        //Returning Response in JSON Format
        return chatRoomInterface.getChatRoomInfo(chatRoomId);
    }

    @GET
    public List<ChatRoom> getAllChatRoomsInfoForUser(@QueryParam("userId") String userId) {
        //Creating Reference
        chatRoomInterface = new ChatRoomService();

        //Returning Response in JSON Format
        return chatRoomInterface.getAllChatRoomsInfoForUser(userId);
    }

    @DELETE
    public void deleteSession(@QueryParam("chatRoomId") String chatRoomId) {
        //Creating Reference
        chatRoomInterface = new ChatRoomService();
        //Calling the deleteSession method
        chatRoomInterface.deleteSession(chatRoomId);
    }
}
