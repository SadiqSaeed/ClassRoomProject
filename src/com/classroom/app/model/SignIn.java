package com.classroom.app.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SignIn {

    private String email;
    private String password;
    private int status;

    public SignIn() {

    }

    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;
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

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SignInInterface [email=" + email + ", password=" + password + ", status=" + status + "]";
    }

}
