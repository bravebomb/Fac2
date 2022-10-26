package com.example.adatest;
import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigInteger;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class userInfoAppActivity extends AppCompatActivity{

    String url = "https://hex.cse.kau.se/~arviblom100/getinfo.php";
    String url2 = "https://hex.cse.kau.se/~arviblom100/isnameindatabase.php";
    public void doesUserPassExistInDatabase(final VolleyCallBack callBack, String name, String pass, Activity activityClass){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().contains("1")){
                    callBack.onSuccess();
                }else{
                    callBack.onFailure();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activityClass, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(activityClass);
        requestQueue.add(request);
    }
    public void doesUserExistInDatabase(final VolleyCallBack callBack, String name, Activity activityClass){
        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().contains("1")){
                    callBack.onSuccess();
                }else{
                    callBack.onFailure();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activityClass, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("loginName", name);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activityClass);
        requestQueue.add(request);
    }

public static String encrypt(String input) throws NoSuchAlgorithmException {
    String result = input;
    if(input != null) {
        MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
        md.update(input.getBytes());
        BigInteger hash = new BigInteger(1, md.digest());
        result = hash.toString(16);
        while(result.length() < 32) { //40 for SHA-1
            result = "0" + result;
        }
    }
    return result;
}

}
