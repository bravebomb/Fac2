package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
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
                final String ourMail = "fistandcrisps@gmail.com";
                final String pass = "Fist@NdCrisps";
                String emailAddress = email.getText().toString();
                Random rnd = new Random();
                int n = 100000 + rnd.nextInt(900000);
                String randomCode = "" + n;
                Properties props = new Properties();
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port","587");
                doesUserExistInDatabase(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
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
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
}