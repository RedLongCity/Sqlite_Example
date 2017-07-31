package com.smitsworks.redlo.sqlite_example.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.facades.CitiesHasCountriesFacadeSingleton;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.util.CountryAdapter;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private CitiesHasCountriesFacadeSingleton cHcFC;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cHcFC = CitiesHasCountriesFacadeSingleton.getOurInstance();//

        ListView listView = (ListView) findViewById(R.id.listCountries);

//        try {
//            List<Country> countries = cHcFC.getAllCountries();
//            final CountryAdapter adapter = new CountryAdapter(getBaseContext(),
//                    R.layout.country_model, countries);
//            listView.setAdapter(adapter);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
                "Костя", "Игорь", "Анна", "Денис", "Андрей" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.nav_cityList){
            Intent intent = new Intent(getBaseContext(),CityActivity.class);
            startActivity(intent);
            finish();
        }else if(id==R.id.nav_searching){
            Intent intent = new Intent(getBaseContext(),SearchingActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
