package com.example.listycitylab3;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener{

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    int pos=-1;

    public void addCity(City city){
        if(pos == -1) {
            cityAdapter.add(city);
        }
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Toronto"
        };
        String[] provinces = {"AB","BC","ON"};

        dataList = new ArrayList<City>();
        for(int i =0; i< cities.length; i++){
            dataList.add(new City(cities[i],provinces[i]));
        }
        cityList = findViewById(R.id.city_list);
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = 1;
                City selected_city = (City) cityList.getItemAtPosition(position);
                new AddCityFragment(selected_city).show(getSupportFragmentManager(),"Edit City");
            }
        });

        fab.setOnClickListener(v ->{
            pos = -1;
            new AddCityFragment().show(getSupportFragmentManager(),"Add City");
        });
    }
}