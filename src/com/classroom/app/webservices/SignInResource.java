package com.classroom.app.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.classroom.app.Interfaces.SignInInterface;
import com.classroom.app.services.SignInService;

@Path("/SignIn")
@Produces(MediaType.TEXT_PLAIN)
public class SignInResource {

    SignInInterface signIn = new SignInService();

    @GET
    @Path("/{userName}/{password}")
    public Response authenticate(@PathParam("userName") String userName, @PathParam("password") String password) {
        return Response.status(200)
                .entity(signIn.authenticateUser(userName, password))
                .build();
    }
}
