package com.example.adatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
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
import java.util.List;
import java.util.Map;

public class ShowImage extends AppCompatActivity {
    public void getImages(List<Model> imagelist, Adapter adapter, String attribute, String searchWord) {
        String url = "https://hex.cse.kau.se/~arviblom100/getvaror.php";
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
                            String store = object.getString("store");
                            info = info.concat(":-");
                            model = new Model(id,urlImage,name,info, store);
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
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("attribute", attribute);
                param.put("searchWord", searchWord);
                return param;
            }
        };
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