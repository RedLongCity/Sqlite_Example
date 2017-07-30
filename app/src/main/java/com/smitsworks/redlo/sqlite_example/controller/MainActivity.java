package com.smitsworks.redlo.sqlite_example.controller;

import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private CitiesHasCountriesFacadeSingleton cHcFC;
    private List<Country> countryList;
    private List<City> citiesList;
    private EditText cityText;
    private EditText countryText;
    private TextView textArea;
    private TextView cityArea;
    private TextView countryArea;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cHcFC = CitiesHasCountriesFacadeSingleton.getOurInstance();

//        cityText = (EditText) findViewById(R.id.txCity);
//        countryText = (EditText) findViewById(R.id.txCountry);
//        textArea = (TextView) findViewById(R.id.txArea);
//        cityArea = (TextView) findViewById(R.id.tvCities);
//        countryArea = (TextView) findViewById(R.id.tvCountries);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addCity(View v) throws IOException {
        if(citiesList==null){
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
        cityArea.setText(citiesList.toString());
    }

    public void addCountry(View v) throws IOException {
        if(countryList==null){
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
        countryArea.setText(countryList.toString());
    }

    public void saveData(View v) throws IOException, SQLException {
        if(countryList==null||citiesList==null){
            LogWriter logwriter = new LogWriter("Persist Data Error");
            logwriter.write("Countries or Cities List is empty");
            return;
        }
        cHcFC.persisitData(citiesList,countryList);
        Log.i("SavedData", "saveData: "+cHcFC.getAllCities().toString());
        Log.i("SavedData", "saveData: "+cHcFC.getAllCountries().toString());
        showTables();
    }

    public void showData(View v) throws SQLException {
        Log.i("ShowData", "show data");
        Log.i("ShowData", "showData: "+cHcFC.getAllCities().toString());
        Log.i("ShowData", "showData: "+cHcFC.getAllCountries().toString());
        showTables();

    }

    public void log(View v){
        Log.i("Log", "Log from button");
    }

    private void showTables() throws SQLException {
        String toAreaStr = "Output";
        toAreaStr=toAreaStr.concat(" : ");
        List<Country> countryList = cHcFC.getAllCountries();
        List<City> cityList;
        for(Country country:countryList){
            cityList = cHcFC.lookupCitiesForCoutries(country);
            if(cityList!=null) {
                toAreaStr=toAreaStr.concat(country.getTitle() + " [" + cityList.toString()+" ]");
            }else{
                toAreaStr="DataBase is Empty";
            }
            toAreaStr=toAreaStr.concat("\n");
        }
        textArea.setText(toAreaStr);
        Log.i("DataBase content",toAreaStr.toString());
    }

    public void clear(View view) throws SQLException {
        cHcFC.clearAllTables();
        Log.i("Cleaning", "currentData: "+cHcFC.getAllCities().toString());
        Log.i("Cleaning", "currentData: "+cHcFC.getAllCountries().toString());
    }
}
