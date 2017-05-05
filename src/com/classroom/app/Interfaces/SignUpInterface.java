package com.classroom.app.Interfaces;

import com.classroom.app.model.SignUp;

import java.util.List;

/**
 * Created by Muhammad Sadiq Saeed on 3/2/2017.
 */
public interface SignUpInterface {

    String createUser(SignUp signUp);

    String checkIfUserExists(String email);

    String checkUserName(String userName);

    List<SignUp> getUserData(String userName);

    String updateUserData(SignUp signUp);

    String updateStatus(String id);

    int checkStatus(String id);

}
