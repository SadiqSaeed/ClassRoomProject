package com.classroom.app.Interfaces;

import com.classroom.app.model.SignUp;

import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 3/2/2017.
 */
public interface SignUpInterface {

    String createUser(String userName, String email, String password);

    String checkIfUserExists(String email);

    String checkUserName(String userName);

    List<SignUp> getUserData(String userName);

    String updateUserData(String id, String userName, String password);

}
