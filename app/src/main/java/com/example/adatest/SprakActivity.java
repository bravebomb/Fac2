package com.example.adatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.yariksoffice.lingver.Lingver;

import java.util.Locale;

public class SprakActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button;
    private String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprak);

        spinner = findViewById(R.id.settingsspinner);
        button = findViewById(R.id.u);
        String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SprakActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                switch (spinner.getSelectedItem().toString()){
                    case "english":
                        setAppLocale("en");
                        break;
                    case "swedish":
                        setAppLocale("sv");
                        break;
                    case "chinese":
                        setAppLocale("zh");
                        break;
                }
            }
        });
    }

    private void setAppLocale(String localeCode){
        Lingver.getInstance().setLocale(this, localeCode);
        Intent starterIntent = getIntent();
        finish();
        startActivity(starterIntent);
    }

}