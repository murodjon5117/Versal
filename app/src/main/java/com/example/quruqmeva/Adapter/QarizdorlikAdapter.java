package com.example.quruqmeva.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quruqmeva.Kerakli;
import com.example.quruqmeva.Modellar.Qarizdorlik;
import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;

import java.util.ArrayList;
import java.util.List;

public class QarizdorlikAdapter extends RecyclerView.Adapter<QarizdorlikAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Qarizdorlik> headlineModelList;
    private List<Qarizdorlik> headlineModelListFilter;

    public QarizdorlikAdapter(Context context, List<Qarizdorlik> headlineModelList) {
        this.context = context;
        this.headlineModelList = headlineModelList;
        this.headlineModelListFilter=headlineModelList;
    }
    @NonNull
    @Override
    public QarizdorlikAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.qarzdorlik_item,parent,false);
        return new QarizdorlikAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final QarizdorlikAdapter.MyViewHolder holder, final int position) {
        Kerakli kerakli =new Kerakli();
        final Qarizdorlik headlineModel=headlineModelListFilter.get(position);
        holder.id.setText(String.valueOf(position+1));
        holder.title.setText(headlineModel.getNomi());
        holder.sana.setText(headlineModel.getSana());
        holder.tel.setText(headlineModel.getNomer());
        holder.summa.setText(kerakli.summa(String.valueOf(headlineModel.getSumma())));


    }

    @Override
    public int getItemCount() {
        return headlineModelListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charcater=charSequence.toString();
                if (charcater.isEmpty()){
                    headlineModelListFilter=headlineModelList;
                }else {
                    List<Qarizdorlik> filterList=new ArrayList<>();
                    for (Qarizdorlik row:headlineModelList){
                        if (row.getNomi().toLowerCase().contains(charcater.toLowerCase())){
                            filterList.add(row);
                        }
                    }
                    headlineModelListFilter=filterList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=headlineModelListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                headlineModelListFilter=(ArrayList<Qarizdorlik>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView sana;
        TextView tel;
        TextView summa;
        TextView izoh;
        View vv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.qarz_nomer_item);
            title = itemView.findViewById(R.id.qarz_klient_item);
            sana = itemView.findViewById(R.id.qarz_sana_item);
            tel=itemView.findViewById(R.id.qarz_tel_item);
            summa=itemView.findViewById(R.id.qarz_summa_item);
            izoh=itemView.findViewById(R.id.qarz_izoh_item);
            vv = itemView;
        }
    }
}
