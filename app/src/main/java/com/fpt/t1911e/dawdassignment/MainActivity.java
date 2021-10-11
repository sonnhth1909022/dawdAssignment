package com.fpt.t1911e.dawdassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.fpt.t1911e.dawdassignment.daymodel.DailyForecasts;
import com.fpt.t1911e.dawdassignment.hourmodel.Root;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemper;
    private TextView tvStatus;
    private RecyclerView rvHour;
    private RecyclerView rvDay;
    private List<DailyForecasts> listDailyForecast;
    private DayAdapter dayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Hours function:

        tvTemper = (TextView) findViewById(R.id.tvTemper);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        getHours();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        rvHour = (RecyclerView) findViewById(R.id.rvHour);
        rvHour.setLayoutManager(layoutManager);


        //Get Days function:

        getDays();

        listDailyForecast = new ArrayList<>();
        dayAdapter = new DayAdapter(MainActivity.this, listDailyForecast);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        rvDay = (RecyclerView) findViewById(R.id.rvDay);
        rvDay.setLayoutManager(layoutManager1);
        rvDay.setAdapter(dayAdapter);

    }
    private void getHours() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.main_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);
        service.getHour().enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (response.body() != null){
                    List<Root> listRoot = response.body();
                    HourAdapter adapter = new HourAdapter(MainActivity.this,listRoot);
                    rvHour.setAdapter(adapter);

                    Root root = listRoot.get(0);
                    tvTemper.setText(root.getTemperature().getValue().intValue()+"ºc");
                    tvStatus.setText(root.getIconPhrase());
                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {

            }
        });
    }

    private void getDays() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.main_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);
        service.getDay().enqueue(new Callback<List<DailyForecasts>>() {
            @Override
            public void onResponse(Call<List<DailyForecasts>> call, Response<List<DailyForecasts>> response) {
                if (response.body() != null){
                    listDailyForecast = response.body();
                    dayAdapter.reloadData(listDailyForecast);
                }
            }

            @Override
            public void onFailure(Call<List<DailyForecasts>> call, Throwable t) {
            }
        });
    }
}