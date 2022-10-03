package com.example.adatest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends userInfoAppActivity {

    EditText loginName, loginPass;
    String url = "https://hex.cse.kau.se/~arviblom100/getinfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = findViewById(R.id.enterButton);
        TextView registerButton = findViewById(R.id.register_button);
        loginName = findViewById(R.id.loginName);
        loginPass = findViewById(R.id.loginPass);
        TextView title = findViewById(R.id.titleId);


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doesUserPassExistInDatabase(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                }, loginName.getText().toString(), loginPass.getText().toString(), MainActivity.this);

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
            }
        });

    }
}