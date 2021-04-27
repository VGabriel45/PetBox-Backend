package springApplication.util;

import springApplication.customers.Customer;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailSender {
    public static void send(String recipient, Customer customer, String password) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        // below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        String myEmail = "vgabrielmarian21@gmail.com";
        String pass = "valytzuu1";


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, pass);
            }
        });

        Message message = prepareMessage(session, myEmail, recipient, customer, password);
        assert message != null;
        Transport.send(message);
        System.out.println("Message sent");

    }

    private static Message prepareMessage(Session session, String myEmail, String recipient, Customer customer,String password) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Welcome to PetBox !");
            message.setText("Hello " + customer.getFirstName() + " " + customer.getLastName() +  " and thank you for using PetBox, please login with this password " + password + " and this username " + customer.getUsername());
            return message;
        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }
}
