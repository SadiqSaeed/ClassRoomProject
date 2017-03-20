package com.classroom.app.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Muhammad Sadiq Saeed on 3/2/2017.
 */
@XmlRootElement
public class SignUp {

    private String id;
    private String userName;
    private String email;
    private String password;
    private int status = 0;

    public SignUp() {

    }

    public SignUp(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
