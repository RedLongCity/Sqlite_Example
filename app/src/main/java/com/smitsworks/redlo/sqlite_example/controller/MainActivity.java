package com.smitsworks.redlo.sqlite_example.controller;

import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.facades.CitiesHasCountriesFacadeSingleton;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.util.DataBaseHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CitiesHasCountriesFacadeSingleton cHcFC;
    private List<Country> countryList;
    private List<City> citiesList;
    private EditText cityText;
    private EditText countryText;
    private TextView textArea;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cHcFC = CitiesHasCountriesFacadeSingleton.getOurInstance();

        cityText = (EditText) findViewById(R.id.txCity);
        countryText = (EditText) findViewById(R.id.txCountry);
        textArea = (TextView) findViewById(R.id.txArea);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addCity(View v) throws IOException {
        if(citiesList.isEmpty()){
            citiesList = new ArrayList<>();
        }
        if(cityText.getText().length()==0){
            LogWriter logwriter = new LogWriter("Add City Error");
            logwriter.write("Area for Cities Titles is Empty");
            return;
        }
        City city = new City();
        city.setTitle(cityText.getText().toString());
        citiesList.add(city);
    }

    public void addCountry(View v) throws IOException {
        if(countryList.isEmpty()){
            countryList = new ArrayList<>();
        }
        if(countryText.getText().length()==0){
            LogWriter logwriter = new LogWriter("Add Country Error");
            logwriter.write("Area for Countries Titles is Empty");
            return;
        }
        Country country = new Country();
        country.setTitle(countryText.getText().toString());
        countryList.add(country);
    }

    public void saveData(View v) throws IOException, SQLException {
        if(countryList.isEmpty()||citiesList.isEmpty()){
            LogWriter logwriter = new LogWriter("Persist Data Error");
            logwriter.write("Countries or Cities List is empty");
            return;
        }
        cHcFC.persisitData(citiesList,countryList);
    }

    private void showData() throws SQLException {
        String toAreaStr = new String();
        List<Country> countryList = cHcFC.getAllCountries();
        List<City> citiesList;
        for(Country country:countryList){
            citiesList = cHcFC.lookupCitiesForCoutries(country);
            if(citiesList!=null) {
                toAreaStr.concat(country.getTitle() + " [" + citiesList.toString()+" ]");
            }else{
                toAreaStr.concat(country.getTitle() + " [" + citiesList.toString()+" ]");
            }
            toAreaStr.concat("\n");
        }
        textArea.setText(toAreaStr);
    }
}
