package com.smitsworks.redlo.sqlite_example.facades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.model.DataPoint;
import com.smitsworks.redlo.sqlite_example.adapters.IncludeAdapter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by redlo on 05.08.2017.
 */

public class FacadeAdapter extends ArrayAdapter<DataPoint> {

    private FacadeSingletonCitiesHasCountries facade;
    private List<? extends DataPoint> items;


    public FacadeAdapter(@NonNull Context context, List<? extends DataPoint> items) {
        super(context, R.layout.exclude_model, (List<DataPoint>) items);
        facade = FacadeSingletonCitiesHasCountries.getInstance();
        this.items=items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        DataPoint dataPoint = items.get(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.exclude_model,null);
        }
        if (dataPoint != null) {
            ((TextView) view.findViewById(R.id.ex_model_text_view)).setText(dataPoint.getTitle());
            try {
                ListView listView = (ListView) view.findViewById(R.id.ex_model_list_view);
                IncludeAdapter adapter = new IncludeAdapter(getContext(),getIncludeList(dataPoint));
                listView.setAdapter(adapter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    private List<? extends DataPoint> getIncludeList(DataPoint dataPoint) throws SQLException {
        if (dataPoint instanceof Country) {
            Country country = (Country) dataPoint;
            return facade.lookupCitiesForCoutries(country);
        }else if(dataPoint instanceof City){
            City city = (City) dataPoint;
            return facade.lookupCountriesForCities(city);
        }
        Log.e("IncludeList", "getIncludeList: avoid of type");
        return null;
    }
}
