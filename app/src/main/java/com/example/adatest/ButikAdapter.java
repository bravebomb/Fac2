package com.example.adatest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

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

public class ButikAdapter extends RecyclerView.Adapter<ButikAdapter.ButikViewHolder> {

    private final  RecycleViewButikerInterface recycleViewButikerInterface;

    Context context;
    ArrayList<ButikerModels> butikerModels;
    String username;
    String url = "https://hex.cse.kau.se/~arviblom100/setfavstore.php";
    String url2 = "https://hex.cse.kau.se/~arviblom100/isfavorite.php";

    public ButikAdapter(Context context, ArrayList<ButikerModels> butikerModels, RecycleViewButikerInterface recycleViewButikerInterface, String username){
        this.context = context;
        this.butikerModels = butikerModels;
        this.recycleViewButikerInterface = recycleViewButikerInterface;
        this.username = username;

    }

    @NonNull
    @Override
    public ButikAdapter.ButikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate((R.layout.butiker_item), parent, false);
        return new ButikAdapter.ButikViewHolder(view, recycleViewButikerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ButikAdapter.ButikViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view

        holder.name.setText(butikerModels.get(position).getButikNamn());
        holder.imageView.setImageResource(butikerModels.get(position).getBild());
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want to display
        return butikerModels.size();
    }

    public void update(){
        notifyDataSetChanged();
    }

    public class ButikViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our layout file
        // kinda like in the onCreate method

        ImageView imageView;
        TextView name;
        ImageButton favoritKnapp;

        public ButikViewHolder(@NonNull View itemView, RecycleViewButikerInterface recycleViewButikerInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.butik_bild);
            name = itemView.findViewById(R.id.butik_namn);
            favoritKnapp = itemView.findViewById(R.id.favknapp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleViewButikerInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recycleViewButikerInterface.onItemClick(pos);
                        }
                    }
                }
            });

            favoritKnapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //favoritKnapp.setImageResource(R.drawable.ic_baseline_favorite_24);

                    int pos = getAdapterPosition();
                    ButikerModels model = butikerModels.get(pos);
                    if(model.getFavstatus() == "0"){
                        favoritKnapp.setImageResource(R.drawable.ic_baseline_favorite_24);
                        model.setFavstatus("1");
                    } else {
                        favoritKnapp.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        model.setFavstatus("0");
                    }

                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> param = new HashMap<String, String>();
                            param.put("username", username);
                            param.put("storename", model.getButikNamn());
                            return param;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(request);
                }

            });
        }
    }
}
