package com.example.adatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profile_activity extends AppCompatActivity {

    TextView FAQButton, logOutButton;
    TextView namn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FAQButton = findViewById(R.id.register_button_FAQ);
        logOutButton = findViewById(R.id.logout);
        namn = findViewById(R.id.profile_name);

        SharedPreferences preferences = getSharedPreferences("loginNamePref", MODE_PRIVATE);
        String userName = (preferences.getString("userNameKey", ""));
        namn.setText(userName);

        FAQButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile_activity.this, FAQActivity.class);
                startActivity(intent);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                Intent intent = new Intent(profile_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}