package com.example.adatest;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        createNotificationChannel();
        long timeAtButtonClick = System.currentTimeMillis();
        long tenSecondsInMillis = 1000 * 10;
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Intent notifyintent = new Intent(MainActivity.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, notifyintent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_DAY * 7, pendingIntent);

        Button enterButton = findViewById(R.id.enterButton);
        TextView registerButton = findViewById(R.id.register_button);
        TextView forgotButton = findViewById(R.id.forgot_button);
        loginName = findViewById(R.id.loginName);
        loginPass = findViewById(R.id.loginPass);
        checkBox = findViewById(R.id.checkbox);
        TextView title = findViewById(R.id.titleId);


        loginNamePref = getSharedPreferences("loginNamePref", MODE_PRIVATE);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");


        if (checkbox.equals("true")) {
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        } else if (checkbox.equals(false)) {

        }

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    doesUserPassExistInDatabase(new VolleyCallBack() {
                        @Override
                        public void onSuccess() {
                            if (isCheckboxChecked) {
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
                    }, loginName.getText().toString(), encrypt(loginPass.getText().toString()), MainActivity.this);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isCheckboxChecked = true;
                } else if (!compoundButton.isChecked()) {
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

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }



    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "StoreReminderChannel";
            String description = "Channel for Lemubit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyStore", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

