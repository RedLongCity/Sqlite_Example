package com.smitsworks.redlo.sqlite_example.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.facades.FacadeSingletonCitiesHasCountries;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.model.DataPoint;
import com.smitsworks.redlo.sqlite_example.adapters.AddItemAdaptor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by redlo on 12.08.2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private FacadeSingletonCitiesHasCountries cHcFC;
    private AddItemAdaptor citiesAdapter;
    private AddItemAdaptor countriesAdapter;
    private List<City> citiesList;
    private List<Country> countriesList;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        ListView listView1 = (ListView) findViewById(R.id.add_act_listView1);
        ListView listView2 = (ListView) findViewById(R.id.add_act_listView2);
        Button backButton = (Button) findViewById(R.id.add_act_button1);
        backButton.setOnClickListener(this);

        cHcFC = FacadeSingletonCitiesHasCountries.getOurInstance();
        intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("country");
        try {
            citiesList = cHcFC.lookupCitiesForCoutries(country);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        citiesAdapter = new AddItemAdaptor(getBaseContext(),this,citiesList);
        listView2.setAdapter(citiesAdapter);
        countriesList = new ArrayList<>();
        countriesList.add(country);
        countriesAdapter = new AddItemAdaptor(getBaseContext(),this,countriesList);
        listView1.setAdapter(countriesAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_item_button_delete:
            DataPoint dataPoint = (DataPoint) v.getTag(1);
            Integer position = (Integer) v.getTag(2);
            if(cHcFC.delete(dataPoint)){
                citiesAdapter.notifyDataSetChanged();
                countriesAdapter.notifyDataSetChanged();
            }
            break;
            case R.id.add_act_button1:
                finishActivity(0);
                break;
        }
    }


}
