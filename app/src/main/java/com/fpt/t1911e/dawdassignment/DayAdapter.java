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
import com.fpt.t1911e.dawdassignment.daymodel.DailyForecasts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<DailyForecasts> listDailyForecast;

    public DayAdapter(Activity activity, List<DailyForecasts> listDailyForecast) {
        this.activity = activity;
        this.listDailyForecast = listDailyForecast;
    }

    public void reloadData(List<DailyForecasts> listDailyForecast){
        this.listDailyForecast = listDailyForecast;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.item_day,parent,false);
        DayHolder holder = new DayHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DayHolder dh = (DayHolder) holder;
        DailyForecasts forecast = listDailyForecast.get(position);
        dh.tvDay.setText(convertTime(forecast.getDate()));
        dh.tvDayMin.setText(forecast.getTemperature().getMinimum().getValue()+"");
        dh.tvDayMax.setText(forecast.getTemperature().getMaximum().getValue()+"");
        String url="";
        if (forecast.getDay().getIcon() <10){
            url = "https://developer.accuweather.com/sites/default/files/0" + forecast.getDay().getIcon() + "-s.png";
        }else {
            url = "https://developer.accuweather.com/sites/default/files/" + forecast.getDay().getIcon() + "-s.png";
        }
        Glide.with(activity).load(url).into(dh.ivDayIcon);
    }

    @Override
    public int getItemCount() {
        return listDailyForecast.size();
    }

    public static class DayHolder extends RecyclerView.ViewHolder {
        private TextView tvDay;
        private ImageView ivDayIcon;
        private TextView tvDayMin;
        private TextView tvDayMax;

        public DayHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            ivDayIcon = (ImageView) itemView.findViewById(R.id.ivDayIcon);
            tvDayMin = (TextView) itemView.findViewById(R.id.tvDayMin);
            tvDayMax = (TextView) itemView.findViewById(R.id.tvDayMax);
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
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String goal = outFormat.format(date);
        return goal;
    }
}
