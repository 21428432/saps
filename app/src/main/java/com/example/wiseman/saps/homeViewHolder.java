package com.example.wiseman.saps;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wiseman.saps.R;

/**
 * Created by Wiseman on 2017-05-04.
 */

public class homeViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView name,time,message;

    public homeViewHolder(View view){
        super(view);

        image = (ImageView)view.findViewById(R.id.ivIcons);
        name = (TextView)view.findViewById(R.id.tvName);
        time = (TextView)view.findViewById(R.id.messageTime);
        message = (TextView)view.findViewById(R.id.displayMessage);

    }
}
