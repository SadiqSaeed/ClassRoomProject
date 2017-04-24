package com.classroom.app.webservices;

import com.classroom.app.Interfaces.SignUpInterface;
import com.classroom.app.model.SignUp;
import com.classroom.app.services.SignUpService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by Muhammad Sadiq Saeed on 3/6/2017.
 */

@Path("/SignUp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SignUpResource {

    SignUpInterface signUpInterface = new SignUpService();
    SignUp signUp = new SignUp();

    @POST
    @Path("/{userName}/{email}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUser(@PathParam("userName") String userName, @PathParam("email") String email,
                            @PathParam("password") String password) {
        signUp.setUserName(userName);
        signUp.setEmail(email);
        signUp.setPassword(password);
        return Response.status(200)
                .entity("Message: " + signUpInterface.createUser(signUp.getUserName(), signUp.getEmail().toLowerCase(), signUp.getPassword()))
                .build();
    }

    @GET
    @Path("/{userName}")
    public List<SignUp> getUserData(@PathParam("userName") String userName) {
        return signUpInterface.getUserData(userName);
    }

    @PUT
    @Path("/{id}/{userName}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(@PathParam("id") String id, @PathParam("userName") String userName,
                               @PathParam("password") String password) {
        return Response.status(200)
                .entity("Message: " + signUpInterface.updateUserData(id, userName, password))
                .build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStatus(@QueryParam("id") String id) {
        return Response.status(200)
                .entity("Message: " + signUpInterface.updateStatus(id))
                .build();
    }


}
