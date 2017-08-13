package com.smitsworks.redlo.sqlite_example.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.facades.FacadeSingletonCitiesHasCountries;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.model.DataPoint;
import com.smitsworks.redlo.sqlite_example.util.AddItemAdaptor;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by redlo on 12.08.2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private FacadeSingletonCitiesHasCountries cHcFC;
    private List<City> citiesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        ListView listView1 = (ListView) findViewById(R.id.add_act_listView1);
        ListView listView2 = (ListView) findViewById(R.id.add_act_listView2);

        cHcFC = FacadeSingletonCitiesHasCountries.getOurInstance();
        Intent intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("country");
        try {
            citiesList = cHcFC.lookupCitiesForCoutries(country);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddItemAdaptor adapter = new AddItemAdaptor(getBaseContext(),this,citiesList);
        listView2.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Integer id = v.getId();
        DataPoint dataPoint = (DataPoint) v.getTag(1);
        if(id==R.id.add_item_button_delete){
            try {
                cHcFC.delete(dataPoint);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{

        }
    }
}
