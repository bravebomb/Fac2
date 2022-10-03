package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.adatest.R;
import com.example.adatest.VolleyCallBack;
import com.example.adatest.userInfoAppActivity;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends userInfoAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        EditText loginName, loginPass;
        loginName = findViewById(R.id.registerLoginName);
        loginPass = findViewById(R.id.registerLoginPass);
        Button registerButton = findViewById(R.id.registerButton);
        String url = "https://hex.cse.kau.se/~arviblom100/Insert.php";

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = loginName.getText().toString();
                String pass = loginPass.getText().toString();
                doesUserExistInDatabase(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegisterUser.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure() {
                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                loginName.setText("");
                                loginPass.setText("");

                                Toast.makeText(RegisterUser.this, "Success!", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterUser.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> param = new HashMap<String, String>();
                                param.put("loginName", name);
                                param.put("loginPass", pass);
                                return param;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(RegisterUser.this);
                        requestQueue.add(request);
                    }
                }, loginName.getText().toString(), loginPass.getText().toString(), RegisterUser.this);
            }
        });

    }
}