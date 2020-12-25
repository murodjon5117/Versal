package com.example.quruqmeva.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quruqmeva.Kerakli;
import com.example.quruqmeva.Modellar.SotilganYuklar;
import com.example.quruqmeva.R;

import org.w3c.dom.Text;

import java.util.List;

public class KunlikSotilgan extends RecyclerView.Adapter<KunlikSotilgan.MyViewHolder> {
    private Context context;
    private List<SotilganYuklar> headlineModelList;

    public KunlikSotilgan(Context context, List<SotilganYuklar> headlineModelList) {
        this.context = context;
        this.headlineModelList = headlineModelList;
    }
    @NonNull
    @Override
    public KunlikSotilgan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sotilgan_item,parent,false);
        return new KunlikSotilgan.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final KunlikSotilgan.MyViewHolder holder, final int position) {
        final SotilganYuklar headlineModel=headlineModelList.get(position);
        Kerakli kerakli=new Kerakli();
        holder.title.setText(headlineModel.getKlient_nomi());
        holder.id.setText(String.valueOf(position+1));
        holder.mahsulot.setText(headlineModel.getMahsulot());
        holder.miqdor.setText(headlineModel.getMiqdor());
        holder.narx.setText(kerakli.summa(headlineModel.getNarx()));
        holder.summa.setText(kerakli.summa(headlineModel.getSumma()));
        holder.sana.setText(headlineModel.getSana());


    }

    @Override
    public int getItemCount() {
        return headlineModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView mahsulot;
        TextView miqdor;
        TextView narx;
        TextView summa;
        TextView sana;
        View vv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.klient_item);
            id=itemView.findViewById(R.id.nomer_item);
            mahsulot=itemView.findViewById(R.id.mahsulot_item);
            miqdor=itemView.findViewById(R.id.miqdor_item);
            narx=itemView.findViewById(R.id.narx_item);
            summa=itemView.findViewById(R.id.summa_item);
            sana=itemView.findViewById(R.id.date);
            vv = itemView;
        }
    }
}
