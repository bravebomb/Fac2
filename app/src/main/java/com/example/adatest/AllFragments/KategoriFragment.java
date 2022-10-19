package com.example.adatest.AllFragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adatest.BreadActivity;
import com.example.adatest.DairyActivity;
import com.example.adatest.DrinkActivity;
import com.example.adatest.ExtraActivity;
import com.example.adatest.GreensActivity;
import com.example.adatest.MeatActivity;
import com.example.adatest.R;
import com.example.adatest.SweetsActivity;
import com.example.adatest.VeganActivity;
import com.example.adatest.VegetarianActivity;


public class KategoriFragment extends Fragment {

    private Button meat;
    private Button bread;
    private Button drink;
    private Button dairy;
    private Button sweets;
    private Button green;
    private Button vegeterian;
    private Button vegan;
    private Button extra;

    public KategoriFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("min notification", "my notificaion", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


       meat = view.findViewById(R.id.meatbutton);
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(),"min notification");
                builder.setContentTitle("hörre du");
                builder.setContentText("Jag vet vart du bor");
                builder.setSmallIcon(R.drawable.shoppingcartpart96);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
                managerCompat.notify(1,builder.build());*/

                Intent in = new Intent(getActivity(), MeatActivity.class);
                startActivity(in);

            }
        });

        green = view.findViewById(R.id.greensbutton);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), GreensActivity.class);
                startActivity(in);
            }
        });

        bread = view.findViewById(R.id.breadbutton);
        bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), BreadActivity.class);
                startActivity(in);
            }
        });

        drink = view.findViewById(R.id.drinkbutton);
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), DrinkActivity.class);
                startActivity(in);
            }
        });

        dairy = view.findViewById(R.id.dairybutton);
        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), DairyActivity.class);
                startActivity(in);
            }
        });

        vegeterian = view.findViewById(R.id.vegetarianbutton);
        vegeterian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), VegetarianActivity.class);
                startActivity(in);
            }
        });

        vegan = view.findViewById(R.id.veganbutton);
        vegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), VeganActivity.class);
                startActivity(in);
            }
        });

        sweets = view.findViewById(R.id.sweetsbutton);
        sweets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), SweetsActivity.class);
                startActivity(in);
            }
        });

        extra = view.findViewById(R.id.extra_button);
        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ExtraActivity.class);
                startActivity(in);
            }
        });

        return view;
    }
}
