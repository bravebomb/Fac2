package com.example.adatest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ButikAdapter extends RecyclerView.Adapter<ButikAdapter.ButikViewHolder> {

    private final  RecycleViewButikerInterface recycleViewButikerInterface;

    Context context;
    ArrayList<ButikerModels> butikerModels;



    public ButikAdapter(Context context, ArrayList<ButikerModels> butikerModels, RecycleViewButikerInterface recycleViewButikerInterface){
        this.context = context;
        this.butikerModels = butikerModels;
        this.recycleViewButikerInterface = recycleViewButikerInterface;

    }

    @NonNull
    @Override
    public ButikAdapter.ButikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate((R.layout.butiker_item), parent, false);
        return new ButikAdapter.ButikViewHolder(view, recycleViewButikerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ButikAdapter.ButikViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view

        holder.name.setText(butikerModels.get(position).getButikNamn());
        holder.imageView.setImageResource(butikerModels.get(position).getBild());
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want to display
        return butikerModels.size();
    }

    public class ButikViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our layout file
        // kinda like in the onCreate method

        ImageView imageView;
        TextView name;
        ImageButton favoritKnapp;

        public ButikViewHolder(@NonNull View itemView, RecycleViewButikerInterface recycleViewButikerInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.butik_bild);
            name = itemView.findViewById(R.id.butik_namn);
            favoritKnapp = itemView.findViewById(R.id.favknapp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleViewButikerInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recycleViewButikerInterface.onItemClick(pos);
                        }
                    }
                }
            });

            favoritKnapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   int pos = getAdapterPosition();
                   ButikerModels model = butikerModels.get(pos);

                }
            });
        }
    }
}
