package com.classroom.app.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.classroom.app.Interfaces.SignInInterface;
import com.classroom.app.model.SignIn;
import com.classroom.app.services.SignInService;

@Path("/SignIn")
@Produces(MediaType.TEXT_PLAIN)
public class SignInResource {

    private SignInInterface signInInterface;

    @GET
    @Path("/{email}/{password}")
    public Response authenticate(@PathParam("email") String email, @PathParam("password") String password) {
        signInInterface = new SignInService();
        return Response.status(200)
                .entity(signInInterface.authenticateUser(email, password))
                .build();
    }
}
