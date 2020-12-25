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
import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;

import java.util.ArrayList;
import java.util.List;

public class HaqqiAdapter extends RecyclerView.Adapter<HaqqiAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<mahsulotOmbor> headlineModelList;
    private List<mahsulotOmbor> headlineModelListFilter;

    public HaqqiAdapter(Context context, List<mahsulotOmbor> headlineModelList) {
        this.context = context;
        this.headlineModelList = headlineModelList;
        this.headlineModelListFilter=headlineModelList;
    }
    @NonNull
    @Override
    public HaqqiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ombor_item,parent,false);
        return new HaqqiAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final HaqqiAdapter.MyViewHolder holder, final int position) {
        Kerakli kerakli=new Kerakli();
        final mahsulotOmbor headlineModel=headlineModelListFilter.get(position);
        holder.title.setText(headlineModel.getNomi());
        holder.id.setText(String.valueOf(position+1));
        holder.miqdor.setText(String.valueOf(kerakli.summa(String.valueOf(headlineModel.getNarxi()))+" so'm"));
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
                    List<mahsulotOmbor> filterList=new ArrayList<>();
                    for (mahsulotOmbor row:headlineModelList){
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
                headlineModelListFilter=(ArrayList<mahsulotOmbor>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView miqdor;
        View vv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.nomer);
            title = itemView.findViewById(R.id.mah_nomi);
            miqdor=itemView.findViewById(R.id.miq);
            vv = itemView;
        }
    }
}