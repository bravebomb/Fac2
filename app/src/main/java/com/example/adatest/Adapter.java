package com.example.adatest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ImageViewHolder>{

    private Context context;
    private List<Model> imagelist;

    public Adapter(Context context, List<Model> imagelist) {
        this.context = context;
        this.imagelist = imagelist;
    }

    public void setNewList(List<Model> newList){
        this.imagelist = newList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_iv, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.nametvimg.setText(imagelist.get(position).getName());
        String info;

            info = imagelist.get(position).getInfo();

        holder.infotvimg.setText(info);
        holder.storetvimg.setText(imagelist.get(position).getStore());
        Glide.with(context).load(imagelist.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{

    TextView nametvimg, infotvimg, storetvimg;
    ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_retrieve);
        nametvimg = itemView.findViewById(R.id.nametvimg);
        infotvimg = itemView.findViewById(R.id.infotvimg);
        storetvimg = itemView.findViewById(R.id.storetvimg);
    }
}