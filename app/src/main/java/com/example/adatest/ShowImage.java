package com.example.adatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowImage extends AppCompatActivity {
    public void getImages(String url, List<Model> imagelist, Adapter adapter) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                imagelist.clear();
                Model model;
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if(success.equals("1")){
                        for(int i = 0; i <jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String urlImage = object.getString("image");
                            String name = object.getString("name");
                            String info = object.getString("info");

                            model = new Model(id,urlImage,name,info);
                            imagelist.add(model);
                            adapter.notifyDataSetChanged();

                        }
                    }else{

                    }
                }catch (Exception e){
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowImage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ShowImage.this);
        requestQueue.add(request);
    }

    public void filterList(String newText, List<Model> imagelist, Adapter adapter) {
        List<Model> newList = new ArrayList<>();
        for(Model model : imagelist){
            if (model.getName().toLowerCase().contains(newText.toLowerCase())){
                newList.add(model);
            }
        }

        if(newText.isEmpty()){
            adapter.setNewList(imagelist);
        } else {
            adapter.setNewList(newList);
        }
    }
}