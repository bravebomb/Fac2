package com.example.adatest.AllFragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adatest.CoopActivity;
import com.example.adatest.IcaActivity;
import com.example.adatest.LidlActivity;
import com.example.adatest.R;
import com.example.adatest.WillysActivity;


public class ButikerFragment extends Fragment {

    private Button icaButton;
    private Button coopButton;
    private Button willysButton;
    private Button lidlButton;

    public ButikerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_butiker, container, false);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("min notification", "my notificaion", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        icaButton = view.findViewById(R.id.ica_button);
        icaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(),"min notification");
                builder.setContentTitle("hörre du");
                builder.setContentText("Jag vet vart du bor");
                builder.setSmallIcon(R.drawable.ica);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
                managerCompat.notify(1,builder.build());

                Intent in = new Intent(getActivity(), IcaActivity.class);
                startActivity(in);

            }
        });

        coopButton = view.findViewById(R.id.coop_button);
        coopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), CoopActivity.class);
                startActivity(in);
            }
        });

        willysButton = view.findViewById(R.id.willys_button);
        willysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), WillysActivity.class);
                startActivity(in);
            }
        });

        lidlButton = view.findViewById(R.id.lidl_button);
        lidlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), LidlActivity.class);
                startActivity(in);
            }
        });

        return view;
    }

}