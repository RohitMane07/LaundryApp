package com.xbiztechventures.lapp.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xbiztechventures.lapp.Model.Track;
import com.xbiztechventures.lapp.R;
import java.util.ArrayList;

/**
 * Created by User on 16-01-2017.
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    ArrayList<Track> track=new ArrayList<Track>();

    public TrackAdapter(ArrayList<Track> track) {
        this.track = track;
    }

    @Override
    public TrackAdapter.TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.track_card,parent,false);
        TrackViewHolder trackViewHolder=new TrackViewHolder(view);
        return trackViewHolder;
    }

    @Override
    public void onBindViewHolder(TrackAdapter.TrackViewHolder holder, int position) {
        Track CON=track.get(position);
        holder.txtorderno.setText((CharSequence) CON.getOrder_no());
        holder.txtdate.setText(CON.getDate()+"");
       /* holder.txtamount_value.setText(CON.getAmount());*/
        holder.txtclothes_value.setText(CON.getCloth());
        if(CON.getStatus().toString().equals("Booked")|| CON.getStatus().toString().equals("Booked(Rescheduled)")){
            holder.txtstatus.setTextColor(Color.BLUE);
        }else if(CON.getStatus().toString().equals("Reschedule")){
            holder.txtstatus.setTextColor(Color.RED);
            holder.btnreschedule.setVisibility(View.VISIBLE);
        }
        else if(CON.getStatus().toString().contentEquals("Delivered")){
            holder.txtstatus.setTextColor(Color.GREEN);
        }
        else if(CON.getStatus().toString().contentEquals("Picked")){
            holder.txtstatus.setTextColor(Color.MAGENTA);
        }
        holder.txtstatus.setText(CON.getStatus());

    }

    @Override
    public int getItemCount() {
        return track.size();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder{
        TextView txtdate;
        TextView txtorderno;
        TextView txtstatus;
        TextView txtclothes_value;
        Button btnreschedule;
       /* TextView txtamount_value;*/


        public TrackViewHolder(View itemView) {
            super(itemView);
            txtdate=(TextView)itemView.findViewById(R.id.date_value);
            txtorderno=(TextView)itemView.findViewById(R.id.order_no);
            txtstatus=(TextView)itemView.findViewById(R.id.status_value);
           /* txtamount_value=(TextView)itemView.findViewById(R.id.amount_value);*/
            txtclothes_value=(TextView)itemView.findViewById(R.id.clothes_value);
            btnreschedule=(Button)itemView.findViewById(R.id.btnreschedule);

        }
    }
}
