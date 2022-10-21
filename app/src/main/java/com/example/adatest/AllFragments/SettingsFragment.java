package com.example.adatest.AllFragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bumptech.glide.load.engine.Resource;
import com.example.adatest.R;
import com.example.adatest.SprakActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingsFragment extends Fragment {

    private Spinner spinner;
    private Button button;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        button = view.findViewById(R.id.settings_sprak);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), SprakActivity.class);
                startActivity(in);
            }
        });

        return view;
    }


    private void setAppLocale(String localeCode){
        Resources res = getActivity().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(config, dm);
    }
}