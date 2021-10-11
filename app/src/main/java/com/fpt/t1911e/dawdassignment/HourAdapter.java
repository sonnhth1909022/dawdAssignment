package com.fpt.t1911e.dawdassignment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fpt.t1911e.dawdassignment.hourmodel.Root;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HourAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Root> listRoot;

    public HourAdapter(Activity activity, List<Root> listRoot) {
        this.activity = activity;
        this.listRoot = listRoot;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.item_hour,parent,false);
        HourHolder holder = new HourHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HourHolder hh = (HourHolder) holder;
        Root root = listRoot.get(position);
        hh.tvTime.setText(convertTime(root.getDateTime()));
        hh.tvTemper.setText(root.getTemperature().getValue()+"");
        String url ="";
        if (root.getWeatherIcon() < 10){
            url = "https://developer.accuweather.com/sites/default/files/0" + root.getWeatherIcon() + "-s.png";
        }else {
            url = "https://developer.accuweather.com/sites/default/files/" + root.getWeatherIcon() + "-s.png";
        }
        Glide.with(activity).load(url).into(hh.ivIcon);
    }

    @Override
    public int getItemCount() {
        return listRoot.size();
    }

    public static class HourHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private ImageView ivIcon;
        private TextView tvTemper;

        public HourHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvTemper = (TextView) itemView.findViewById(R.id.tvTemper);
        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }
}
