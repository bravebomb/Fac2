package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ForgotPassword extends userInfoAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Button sendButton = findViewById(R.id.sendButton);
        EditText email = findViewById(R.id.email);
        String emailAddress = email.getText().toString();
        String url = "https://hex.cse.kau.se/~arviblom100/setRecoveryCode.php";

        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        String randomCode = "" + n;

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ForgotPassword.this, "Sent reset instructions if user exists!", Toast.LENGTH_SHORT).show();
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
            }
        });



        /*
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
         */

    }
}