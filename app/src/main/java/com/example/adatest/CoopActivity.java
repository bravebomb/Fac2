package com.example.adatest;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CoopActivity extends ShowImage {

    SearchView searchViewCoop;
    RecyclerView recyclerView;
    List<Model> imagelist;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coop);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        searchViewCoop = findViewById(R.id.search_view_coop);
        searchViewCoop.clearFocus();

        recyclerView = findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        imagelist = new ArrayList<>();
        adapter = new Adapter(this, imagelist);
        recyclerView.setAdapter(adapter);
        getImages(imagelist,adapter, "store","coop");

        searchViewCoop.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                filterList(newText,imagelist,adapter);
                return true;
            }
        });
    }
}