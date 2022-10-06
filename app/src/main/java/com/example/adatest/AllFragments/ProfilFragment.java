package com.example.adatest.AllFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adatest.CoopActivity;
import com.example.adatest.FAQActivity;
import com.example.adatest.R;

public class ProfilFragment extends Fragment {

    private TextView FAQButton;


    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        FAQButton = view.findViewById(R.id.register_button_FAQ);
        FAQButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), FAQActivity.class);
                startActivity(in);
            }
        });
        return view;
    }
}