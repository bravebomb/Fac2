package com.example.adatest.AllFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adatest.ButikerActivity;
import com.example.adatest.FragmentAdapter;
import com.example.adatest.R;

public class HemFragment extends Fragment {

    private Button Butiker;


    public HemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hem, container, false);

        Butiker = view.findViewById(R.id.butikerknapp);
        Butiker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ButikerActivity.class);
                startActivity(in);
            }
        });
        return view;
    }
}