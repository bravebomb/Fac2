package com.example.adatest;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;

    private Session session;
    private String email, subject, message;

    public JavaMailAPI(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.zoho.in");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // If ssl is your concern, uncomment this line
        properties.setProperty("mail.smtp.socketFactory.fallback", "true");
        properties.setProperty("mail.smtp.port", "587");//HTTP - 587 , HTTPS - 465
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.zoho.in");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug.auth", "true");
        properties.setProperty( "mail.pop3.socketFactory.fallback", "false");



        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("fistsandcrisps@zohomail.eu", "uG0Pc3WnH48v");
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Utils.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }
}