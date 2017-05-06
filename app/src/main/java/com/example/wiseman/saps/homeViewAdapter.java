package com.example.wiseman.saps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Wiseman on 2017-05-04.
 */

public class homeViewAdapter extends RecyclerView.Adapter<homeViewHolder> implements  onActivityResult{
     TextView appName;
    public List<homeItems> list;
    private Context context,c;

    String email, subject, message, attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;

    SharedPreferences data_stored;

    public homeViewAdapter(Context context,List<homeItems>list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public homeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_list,null);
        c = parent.getContext();
        homeViewHolder viewHolder = new homeViewHolder(v);
         appName = (TextView)v.findViewById(R.id.tvName);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(homeViewHolder holder, final int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        Animation upAnim = AnimationUtils.loadAnimation(context,R.anim.translate);
        upAnim.reset();
        holder.itemView.clearAnimation();
        holder.itemView.setAnimation(upAnim);

        appName.clearAnimation();
        appName.startAnimation(upAnim);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                process (position);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            /**047* Get Path048*/


            TelephonyManager tm = (TelephonyManager)(c).getSystemService(Context.TELEPHONY_SERVICE);

            //---get the SIM card ID---
            String simID = tm.getSimSerialNumber();
            if (simID != null)
                Toast.makeText(context,"SIM card ID: " + simID,Toast.LENGTH_LONG).show();

            //---get the phone number---
            String telNumber = tm.getLine1Number();
            if (telNumber != null)
                Toast.makeText(context, "Phone number: " + telNumber, Toast.LENGTH_LONG).show();

            //---get the IMEI number---
            String IMEI = tm.getDeviceId();
            if (IMEI != null)
                Toast.makeText(context,"IMEI number: " + IMEI,Toast.LENGTH_LONG).show();


            data_stored = (c).getApplicationContext().getSharedPreferences("Users",(c).getApplicationContext().MODE_PRIVATE);
            String phone = data_stored.getString("Phone","");


            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = context.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            attachmentFile = cursor.getString(columnIndex);
            Log.e("Attachment Path:", attachmentFile);
            URI = Uri.parse("file://" + attachmentFile);
            cursor.close();

            try {
                email = "thammy202@gmail.com";
                subject = "Criminal Report";

                message = "Phone number: " + phone+"\n"+ "SIM card ID: " + simID+"\n"+ "IMEI number: " + IMEI;

                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);


                if (URI != null) {

                    emailIntent.putExtra(Intent.EXTRA_STREAM, URI);

                }

                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,message);
                ((Activity)c).startActivity(Intent.createChooser(emailIntent, "Sending email..."));

            } catch (Throwable t) {

                 Toast.makeText(context, "Request failed try again: " + t.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        ((Activity)c).startActivityForResult(Intent.createChooser(intent, "Complete action using"),PICK_FROM_GALLERY);

    }

    public void openVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        ((Activity)c).startActivityForResult(Intent.createChooser(intent, "Complete action using"),PICK_FROM_GALLERY);
    }

    public void openAudio() {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        ((Activity)c).startActivityForResult(Intent.createChooser(intent, "Complete action using"),PICK_FROM_GALLERY);
    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void process (int pos){
        switch (pos){
            case 0:
                openGallery();
                break;
            case 1:
                Toast.makeText(context, "video", Toast.LENGTH_SHORT).show();
                openVideo();
                break;
            case 2:
                Toast.makeText(context, "dial", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0613350302"));
                ((Activity)c).startActivity(intent);
                break;
            case 3:
                Toast.makeText(context, "record", Toast.LENGTH_SHORT).show();
                openAudio();
                break;
        }
    }


}
