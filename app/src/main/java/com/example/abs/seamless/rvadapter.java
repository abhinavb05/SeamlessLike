package com.example.abs.seamless;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ab's on 23-02-2017.
 */

public class rvadapter extends RecyclerView.Adapter<rvadapter.ViewHolder>{

    List<card_data> data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cards, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).name);
        holder.spcl.setText(data.get(position).spl);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView logo;
        TextView title;
        TextView spcl;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            logo = (ImageView)itemView.findViewById(R.id.logo);
            title = (TextView)itemView.findViewById(R.id.title);
            spcl = (TextView)itemView.findViewById(R.id.spcl);
        }
    }

    public rvadapter(List<card_data> data)
    {
        this.data=data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
