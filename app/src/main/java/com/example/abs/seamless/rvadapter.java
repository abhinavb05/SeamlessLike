package com.example.abs.seamless;

import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        ImageView s1,s2,s3,s4,s5;
        TextView rtng;
        ImageView fav;
        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            logo = (ImageView)itemView.findViewById(R.id.logo);
            title = (TextView)itemView.findViewById(R.id.title);
            spcl = (TextView)itemView.findViewById(R.id.spcl);
            s1 = (ImageView)itemView.findViewById(R.id.s1);
            s2 = (ImageView)itemView.findViewById(R.id.s2);
            s3 = (ImageView)itemView.findViewById(R.id.s3);
            s4 = (ImageView)itemView.findViewById(R.id.s4);
            s5 = (ImageView)itemView.findViewById(R.id.s5);
            rtng = (TextView)itemView.findViewById(R.id.rtng);
            fav = (ImageView)itemView.findViewById(R.id.fav);
        }
    }

    rvadapter(List<card_data> data)
    {
        this.data=data;
    }
}