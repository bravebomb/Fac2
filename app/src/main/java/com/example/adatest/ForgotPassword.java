package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ForgotPassword extends userInfoAppActivity {

    private EditText email;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        btn = findViewById(R.id.sendButton);
        email = findViewById(R.id.email);

        String url = "https://hex.cse.kau.se/~arviblom100/setRecoveryCode.php";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailAddress = email.getText().toString();
                Random rnd = new Random();
                int n = 100000 + rnd.nextInt(900000);
                String randomCode = "" + n;

                try {
                    String stringSenderEmail = "fistandcrisps@gmail.com";
                    String stringReceiverEmail = "arvidblomberg13@gmail.com";
                    String stringPasswordSenderEmail = "roykvhjnbdiuobkr";

                    String stringHost = "smtp.gmail.com";

                    Properties properties = System.getProperties();

                    properties.put("mail.smtp.host", stringHost);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                        }
                    });

                    MimeMessage mimeMessage = new MimeMessage(session);
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

                    mimeMessage.setSubject("Subject: Android App email");
                    mimeMessage.setText("Hello Programmer, \n\nProgrammer World has sent you this 2nd email. \n\n Cheers!\nProgrammer World");

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(mimeMessage);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();

                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                /*
                Properties props = new Properties();
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port","587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator(){
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(ourMail,pass);
                            }
                        });
                try{
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(ourMail));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
                    message.setSubject("Bra jobbat göbb");
                    message.setText("Hur kan man glömma sitt lösenord?" +
                            "aja du vet att det är 2022, du har en mobil skriv ner det nästa gång....." + " Här är din nya kod och snälla skriv ner den så vi slipper släppa ut mer koldioxid pågrund avf dig!!!!"
                            + " kod:" + randomCode + " så ja gå in på denna länk nu...");
                    Transport.send(message);
                    Toast.makeText(getApplicationContext(), "email send sucessfully", Toast.LENGTH_LONG).show();
                } catch (MessagingException e){
                    throw new RuntimeException(e);
                }

                 */
                /*
                doesUserExistInDatabase(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {


                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                email.setText("");

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ForgotPassword.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> param = new HashMap<String, String>();
                                param.put("loginName", emailAddress);
                                param.put("code", randomCode);
                                return param;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(ForgotPassword.this);
                        requestQueue.add(request);

                    }

                    @Override
                    public void onFailure() {

                    }
                }, emailAddress, ForgotPassword.this);

                 */
            }
        });
    }

}