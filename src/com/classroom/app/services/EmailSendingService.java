package com.classroom.app.services;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Muhammad Sadiq Saeed on 3/8/2017.
 */
public class EmailSendingService {

    private String to = null;
    private String from = null;
    private String host = null;
    private String password = null;
    private String subject = null;
    private String text = null;
    private String message = null;

    public String sendMail(String email, String userName) {

        to = email;
        from = "classroomapp2017@gmail.com";
        password = "classroomapp123456789";
        host = "smtp.gmail.com";
        subject = "Account Verification mail";
        text = "Dear '" + userName + "' \n \n \n To complete your registration \n with ClassRoom please click the" +
                "\n link below to verify your email \n address \n \n \n If you didn't sign up; please \n" +
                " ignore this email";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);

            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(text);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.send(mimeMessage);

            message = "Verification Mail has been send on your email address ";

        } catch (MessagingException mesg) {
            System.err.println(mesg.getMessage());
        }


        return message;
    }

}
