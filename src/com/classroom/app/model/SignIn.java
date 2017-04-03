package com.classroom.app.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SignIn {

    private String userName_email;
    private String password;
    private int status;

    public SignIn() {

    }

    public SignIn(String userName_email, String password) {
        this.userName_email = userName_email;
        this.password = password;
    }

    public String getUserName_email() {
        return userName_email;
    }

    public void setUserName_email(String userName_email) {
        this.userName_email = userName_email;
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

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SignInInterface [userName_email=" + userName_email + ", password=" + password + ", status=" + status + "]";
    }

}
