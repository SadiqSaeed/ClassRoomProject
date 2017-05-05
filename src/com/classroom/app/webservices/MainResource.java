package com.classroom.app.webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Muhammad Sadiq Saeed on 3/5/2017.
 */
@ApplicationPath("webapi")
public class MainResource extends Application {

    public Set<Class<?>> getAllClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(SignInResource.class);
        set.add(SignUpResource.class);
        set.add(ChatRoomResource.class);
        set.add(ChatRoomUsersResource.class);
        set.add(MessageResource.class);
        return set;
    }

}
