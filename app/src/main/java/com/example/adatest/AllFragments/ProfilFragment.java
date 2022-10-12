package com.example.adatest.AllFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adatest.CoopActivity;
import com.example.adatest.FAQActivity;
import com.example.adatest.MainActivity;
import com.example.adatest.R;

public class ProfilFragment extends Fragment {

    private TextView FAQButton;
    private TextView logOutButton;
    private TextView namn;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        FAQButton = view.findViewById(R.id.register_button_FAQ);
        logOutButton = view.findViewById(R.id.logout);
        namn = view.findViewById(R.id.profile_name);

        SharedPreferences preferences = getActivity().getSharedPreferences("loginNamePref", Context.MODE_PRIVATE);
        String userName = (preferences.getString("userNameKey", ""));
        namn.setText(userName);

        FAQButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), FAQActivity.class);
                startActivity(in);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
            }
        });
        return view;
    }
}