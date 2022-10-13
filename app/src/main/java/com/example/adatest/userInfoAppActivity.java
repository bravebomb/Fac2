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
import java.security.MessageDigest;
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
    public void doesUserExistInDatabase(final VolleyCallBack callBack, String name, String pass, Activity activityClass){
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

    public static byte[] encryptMD5(byte[] data) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public String encrypt(byte[] data){
        BigInteger md5Data = null;

        try {
            md5Data = new BigInteger(1, encryptMD5(data));
        }catch (Exception e){
            e.printStackTrace();
        }
        return md5Data.toString();
    }
}
