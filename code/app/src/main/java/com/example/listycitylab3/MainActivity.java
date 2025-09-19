package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;


    public static int editIndex;
    public static boolean editState = false;

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void editCity(int index, City city) {
        dataList.set(index, city);
        cityAdapter.notifyDataSetChanged();
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };

        dataList = new ArrayList<City>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }
        
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        Toast.makeText(MainActivity.this, "Tap city to edit.", Toast.LENGTH_LONG).show();

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            editState = false;
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

//        FloatingActionButton fab2 = findViewById(R.id.button_edit_city);
//        fab2.setOnClickListener(v -> {
//            editState = true;
//            Toast.makeText(MainActivity.this, "Please select the city you want to edit.", Toast.LENGTH_SHORT).show();
//        });



        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editState = true;
                editIndex = position;
                new AddCityFragment().show(getSupportFragmentManager(), "Edit City");
            }
        });


    }
}