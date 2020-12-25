package com.example.quruqmeva.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quruqmeva.Modellar.Savdo;
import com.example.quruqmeva.R;

import java.util.List;

public class SavdoAdapter extends RecyclerView.Adapter<SavdoAdapter.MyViewHolder>{
private Context context;
private List<Savdo> headlineModelList;
private MyViewHolder.OnLongClick   click;
public SavdoAdapter(Context context, List<Savdo> headlineModelList, MyViewHolder.OnLongClick click) {
        this.context = context;
        this.headlineModelList = headlineModelList;
        this.click=click;
        }

    public SavdoAdapter(Context context, List<Savdo> headlineModelList) {
        this.context = context;
        this.headlineModelList = headlineModelList;
    }

    @NonNull
@Override
public SavdoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new SavdoAdapter.MyViewHolder(view);
        }
@Override
public void onBindViewHolder(@NonNull final SavdoAdapter.MyViewHolder holder, final int position) {
final Savdo headlineModel=headlineModelList.get(position);
        holder.title.setText(String.valueOf(headlineModel.getYukchi_nomi()));
        holder.id.setText(String.valueOf((position+1)));
        holder.mahsulot.setText(String.valueOf(headlineModel.getMahsulot_nomi()));
        holder.miqdor.setText(String.valueOf(headlineModel.getMiqdor()));
        holder.narx.setText(String.valueOf(headlineModel.getNarx()));
        holder.summa.setText(String.valueOf(headlineModel.getTolov()));
        holder.vv.setOnLongClickListener(new AdapterView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                 headlineModelList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, headlineModelList.size());
                click.OnItemLongClick(headlineModel,position);
                return false;
            }
        });

        }


@Override
public int getItemCount() {
        return headlineModelList.size();
        }

public static class MyViewHolder extends RecyclerView.ViewHolder {
    TextView id;
    TextView title;
    TextView mahsulot;
    TextView miqdor;
    TextView narx;
    TextView summa;
    View vv;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.klient_item);
        id=itemView.findViewById(R.id.nomer_item);
        mahsulot=itemView.findViewById(R.id.mahsulot_item);
        miqdor=itemView.findViewById(R.id.miqdor_item);
        narx=itemView.findViewById(R.id.narx_item);
        summa=itemView.findViewById(R.id.summa_item);
        vv = itemView;
    }
 public  interface  OnLongClick {
         void OnItemLongClick(Savdo model ,int position);
 }
}

}
