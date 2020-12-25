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

import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;

import java.util.ArrayList;
import java.util.List;

public class OmborAdapter extends RecyclerView.Adapter<OmborAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<mahsulotOmbor> headlineModelList;
    private List<mahsulotOmbor> headlineModelListFilter;

    public OmborAdapter(Context context, List<mahsulotOmbor> headlineModelList) {
        this.context = context;
        this.headlineModelList = headlineModelList;
        this.headlineModelListFilter=headlineModelList;
    }
    @NonNull
    @Override
    public OmborAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ombor_item,parent,false);
        return new OmborAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final OmborAdapter.MyViewHolder holder, final int position) {
        final mahsulotOmbor headlineModel=headlineModelListFilter.get(position);
        holder.title.setText(headlineModel.getNomi());
        holder.id.setText(String.valueOf(position+1));
        holder.miqdor.setText(String.valueOf(headlineModel.getMiqdor()+" kg"));
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
