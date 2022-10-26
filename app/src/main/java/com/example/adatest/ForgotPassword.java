package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.security.GeneralSecurityException;
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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String url = "https://hex.cse.kau.se/~arviblom100/setRecoveryCode.php";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailAddress = email.getText().toString();
                Random rnd = new Random();
                int n = 100000 + rnd.nextInt(900000);
                String randomCode = "" + n;
                doesUserExistInDatabase(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        //sendEmail(randomCode);
                        AlertDialog alertDialog = new AlertDialog.Builder(ForgotPassword.this).create();
                        alertDialog.setTitle("Mail function NYI");
                        alertDialog.setMessage("The mail function is not yet implemented, please use this code: " + randomCode + " on the website below to reset your password");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.show();
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
    }
    private void sendEmail(String randomCode) {
        String mEmail = email.getText().toString();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, Utils.EMAIL, "test", randomCode);

        javaMailAPI.execute();
    }

}