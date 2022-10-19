package com.example.adatest.AllFragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adatest.ButikAdapter;
import com.example.adatest.ButikerActivity;
import com.example.adatest.ButikerModels;
import com.example.adatest.CoopActivity;
import com.example.adatest.FirstActivity;
import com.example.adatest.FragmentAdapter;
import com.example.adatest.HemkopActivity;
import com.example.adatest.IcaActivity;
import com.example.adatest.MainActivity;
import com.example.adatest.Model;
import com.example.adatest.Profile_activity;
import com.example.adatest.R;
import com.example.adatest.RecycleViewButikerInterface;
import com.example.adatest.VolleyCallBack;
import com.example.adatest.WillysActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HemFragment extends Fragment implements RecycleViewButikerInterface {

    private TextView Butiker;
    private ImageButton profilKnapp;
    RecyclerView recyclerView;
    ArrayList<ButikerModels> butikerModels = new ArrayList<>();
    int[] butikerBilder = {R.drawable.ica,R.drawable.coop,R.drawable.willys,R.drawable.hemkop};
    String url2 = "https://hex.cse.kau.se/~arviblom100/isfavorite.php";
    String _response;
    String userName;

    ButikAdapter adapter;

    public HemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hem, container, false);
        Butiker = view.findViewById(R.id.butikerknapp);
        recyclerView = view.findViewById(R.id.recyclerHem);
        SharedPreferences preferences = getContext().getSharedPreferences("loginNamePref", MODE_PRIVATE);
        userName = preferences.getString("userNameKey", "");




        //setUpButiker();


        Butiker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ButikerActivity.class);
                startActivity(in);
            }
        });

        profilKnapp = view.findViewById(R.id.profilButton);
        profilKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Profile_activity.class);
                startActivity(in);
            }
        });

        return view;
    }

    public void checkFavorites(final VolleyCallBack callBack, String userName, Activity activityClass){
        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().contains("1")){
                    _response = response;
                    callBack.onSuccess();
                }else{
                    _response = response;
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
                param.put("username", userName);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activityClass);
        requestQueue.add(request);
    }

    private int getStoreImage(String name){
        switch (name){
            case "Ica":
                return R.drawable.ica;
            case "Coop":
                return R.drawable.coop;
            case "Willys":
                return R.drawable.willys;
            case "Hemköp":
                return R.drawable.hemkop;
            default:
                return R.drawable.ica;
        }
    }
    @Override
    public void onResume(){
        updateFavoriteStores(userName);
        super.onResume();
    }
    @Override
    public void onItemClick (int position) {
        Intent intent;

        String temp = butikerModels.get(position).getButikNamn();
        switch (temp){
            case "Ica":
                intent = new Intent(getContext(), IcaActivity.class);
                startActivity(intent);
                break;
            case "Coop":
                intent = new Intent(getContext(), CoopActivity.class);
                startActivity(intent);
                break;
            case "Willys":
                intent = new Intent(getContext(), WillysActivity.class);
                startActivity(intent);
                break;
            case "Hemköp":
                intent = new Intent(getContext(), HemkopActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void updateFavoriteStores(String userName){
        butikerModels.clear();

        checkFavorites(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                try{
                    JSONObject jsonObject = new JSONObject(_response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if(success.equals("1")){
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String storename = object.getString("storename");
                            butikerModels.add(new ButikerModels(storename,getStoreImage(storename)));
                        }

                        adapter = new ButikAdapter(getActivity(),
                                butikerModels,HemFragment.this, userName);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }else{

                    }
                }catch (Exception e){
                }
            }
            @Override
            public void onFailure() {

            }
        }, userName, getActivity());
    }

    private void setUpButiker(String temp){
        String[] butikNamn = getResources().getStringArray(R.array.Butiknamn);

        for(int i = 0; i< butikNamn.length;i++){
            //Toast.makeText(getContext(), butikNamn[i], Toast.LENGTH_SHORT).show();
            butikerModels.add(new ButikerModels(temp,butikerBilder[i]));
        }
    }
}