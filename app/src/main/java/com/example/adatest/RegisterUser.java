package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends userInfoAppActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initDatePicker();
        dateButton = findViewById(R.id.datepicker);
        dateButton.setText(getTodaysDate());

        EditText loginName, loginPass, loginIrlName, loginPhone, loginDob, loginAddress;
        loginName = findViewById(R.id.registerLoginName);
        loginPass = findViewById(R.id.registerLoginPass);
        loginIrlName = findViewById(R.id.irlname);
        loginPhone = findViewById(R.id.phone);
        loginAddress = findViewById(R.id.address);
        Button registerButton = findViewById(R.id.registerButton);
        String url = "https://hex.cse.kau.se/~arviblom100/Insert.php";

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = loginName.getText().toString();
                //String pass = loginPass.getText().toString();
                //pass = encrypt(pass.getBytes());
                String pass = loginPass.getText().toString();
                String irlname = loginIrlName.getText().toString();
                String phone = loginPhone.getText().toString();
                //String dob = loginDob.getText().toString();
                String address = loginAddress.getText().toString();




                doesUserExistInDatabase(new VolleyCallBack() {
                    String hashPass;
                    {
                        try {
                            hashPass = encrypt(pass);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegisterUser.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure() {
                        if(!isValidEmail(name)){
                            Toast.makeText(RegisterUser.this, "Mail är fel!", Toast.LENGTH_SHORT).show();
                        }else{ //om allt är ok!
                            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    loginName.setText("");
                                    loginPass.setText("");
                                    loginIrlName.setText("");
                                    loginPhone.setText("");
                                    loginAddress.setText("");

                                    Toast.makeText(RegisterUser.this, "Success!", Toast.LENGTH_SHORT).show();
                                    finish();
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
                                    param.put("loginPass", hashPass);
                                    param.put("irlname", irlname);
                                    param.put("phone", phone);
                                    param.put("dob", dateButton.getText().toString());
                                    param.put("address", address);
                                    return param;
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(RegisterUser.this);
                            requestQueue.add(request);
                        }
                    }
                }, loginName.getText().toString(), RegisterUser.this);
            }
        });

    }

        private String getTodaysDate() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            month = month + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return makeDateString(day,month,year);
        }


        public static boolean isValidEmail(CharSequence target) {
            return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
        }

        private void initDatePicker() {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = makeDateString(day, month, year);
                    dateButton.setText(date);
                }
            };
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_LIGHT;

            datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        }

        private String makeDateString(int day, int month, int year) {
            return getMonthFormat(month)+ " " + day + " " + year;
        }

        private String getMonthFormat(int month) {
            switch (month){
                case 1:
                    return "JAN";
                case 2:
                    return "FEB";
                case 3:
                    return "MAR";
                case 4:
                    return "APR";
                case 5:
                    return "MAY";
                case 6:
                    return "JUN";
                case 7:
                    return "JUL";
                case 8:
                    return "AUG";
                case 9:
                    return "SEP";
                case 10:
                    return "OKT";
                case 11:
                    return "NOV";
                default:
                    return "DEC";
            }
        }

        public void openDatePicker(View view) {
            datePickerDialog.show();
        }
}
