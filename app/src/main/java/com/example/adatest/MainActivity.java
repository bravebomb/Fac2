package com.example.adatest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends userInfoAppActivity {

    EditText loginName, loginPass;
    CheckBox checkBox;
    Boolean isCheckboxChecked = false;
    String url = "https://hex.cse.kau.se/~arviblom100/getinfo.php";
    SharedPreferences loginNamePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = findViewById(R.id.enterButton);
        TextView registerButton = findViewById(R.id.register_button);
        loginName = findViewById(R.id.loginName);
        loginPass = findViewById(R.id.loginPass);
        checkBox = findViewById(R.id.checkbox);
        TextView title = findViewById(R.id.titleId);


        loginNamePref = getSharedPreferences("loginNamePref", MODE_PRIVATE);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");


        if(checkbox.equals("true")){
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        }else if(checkbox.equals(false)){

        }

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doesUserPassExistInDatabase(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        if(isCheckboxChecked){
                            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("remember", "true");
                            editor.apply();
                        }
                        SharedPreferences.Editor loginEditor = loginNamePref.edit();
                        loginEditor.putString("userNameKey", loginName.getText().toString());
                        loginEditor.apply();
                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure() {
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember", "false");
                        editor.apply();
                        Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                }, loginName.getText().toString(), encrypt(loginPass.getText().toString().getBytes()), MainActivity.this);

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isCheckboxChecked = true;
                }else if(!compoundButton.isChecked()){
                    isCheckboxChecked = false;
                }
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