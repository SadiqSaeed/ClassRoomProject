package com.classroom.app.webservices;

import com.classroom.app.Interfaces.ChatRoomInterface;
import com.classroom.app.services.ChatRoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Muhammad Sadiq Saeed on 4/15/2017.
 */
@Path("/ChatRoom")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatRoomResource {

    private ChatRoomInterface chatRoomInterface = new ChatRoomService();

    @POST
    @Path("/{title}/{description}/{groupType}/{groupAdmin}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createSession(@PathParam("title") String title, @PathParam("description") String description,
                                  @PathParam("groupType") int groupType, @PathParam("groupAdmin") String groupAdmin) {
        return Response.status(200)
                .entity(chatRoomInterface.createSession(title, description, groupType, groupAdmin))
                .build();
    }

}
