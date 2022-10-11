package com.example.adatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ButikerActivity extends AppCompatActivity implements RecycleViewButikerInterface {

    ArrayList<ButikerModels> butikerModels = new ArrayList<>();

    int[] butikerBilder = {R.drawable.coop,R.drawable.coop,R.drawable.willys,R.drawable.lidl};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butiker);

        RecyclerView recyclerView = findViewById(R.id.rycycler_butik);

        setUpButiker();
        //adapter alltid efter setup
        ButikAdapter adapter = new ButikAdapter(this,
                butikerModels,this);

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
                intent = new Intent(this, LidlActivity.class);
                startActivity(intent);
                break;
        }
    }
}