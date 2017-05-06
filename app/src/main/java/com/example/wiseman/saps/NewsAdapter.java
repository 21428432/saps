package com.example.wiseman.saps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

/**
 * Created by EDU01 on 5/3/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<homeViewHolder>{

    public List<homeItems> list ;
    private Context context;


    public NewsAdapter(Context context,List<homeItems> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public homeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,null);
        homeViewHolder viewHolder = new homeViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final homeViewHolder holder, int position) {
        holder.time.setText(list.get(position).getTime());
        holder.message.setText(list.get(position).getMessage());

        Animation upAnim = AnimationUtils.loadAnimation(context,R.anim.translate);
        upAnim.reset();
        holder.itemView.clearAnimation();
        holder.itemView.setAnimation(upAnim);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
