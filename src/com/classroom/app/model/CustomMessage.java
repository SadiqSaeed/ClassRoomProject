package com.classroom.app.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Muhammad Sadiq Saeed on 4/3/2017.
 */
@XmlRootElement
public class CustomMessage {

    private int id;
    private String myMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "id=" + id +
                ", myMessage='" + myMessage + '\'' +
                '}';
    }
}
