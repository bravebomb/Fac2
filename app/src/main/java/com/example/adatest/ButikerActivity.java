package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ButikerActivity extends AppCompatActivity implements RecycleViewButikerInterface {

    ArrayList<ButikerModels> butikerModels = new ArrayList<>();

    int[] butikerBilder = {R.drawable.coop,R.drawable.coop,R.drawable.willys,R.drawable.lidl};
    String url2 = "https://hex.cse.kau.se/~arviblom100/isfavorite.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butiker);

        SharedPreferences preferences = getSharedPreferences("loginNamePref", MODE_PRIVATE);
        String userName = preferences.getString("userNameKey", "");

        RecyclerView recyclerView = findViewById(R.id.rycycler_butik);

        setUpButiker();
        //adapter alltid efter setup
        ButikAdapter adapter = new ButikAdapter(this,
                butikerModels,this, userName);


        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Model model;
                int temp = 0;
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if(success.equals("1")){
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String storename = object.getString("storename");
                            for(ButikerModels bModel : butikerModels){
                                if(bModel.getButikNamn().equals(storename)){
                                    bModel.setFavstatus("1");
                                    View row = recyclerView.getLayoutManager().findViewByPosition(temp);
                                    ImageButton favknapp = row.findViewById(R.id.favknapp);
                                    favknapp.setImageResource(R.drawable.ic_baseline_favorite_24);
                                }
                                temp++;
                            }
                            temp = 0;
                        }
                    }else{

                    }
                }catch (Exception e){
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ButikerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("username", userName);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ButikerActivity.this);
        requestQueue.add(request);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void setUpButiker(){
        String[] butikNamn = getResources().getStringArray(R.array.Butiknamn);

        for(int i = 0; i< butikNamn.length;i++){
            butikerModels.add(new ButikerModels(butikNamn[i],butikerBilder[i]));
        }
    }

    @Override
    public void onItemClick (int position) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(this, IcaActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, CoopActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, WillysActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, HemkopActivity.class);
                startActivity(intent);
                break;
        }
    }
}