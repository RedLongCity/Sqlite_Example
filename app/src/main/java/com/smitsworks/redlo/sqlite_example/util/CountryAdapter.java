package com.smitsworks.redlo.sqlite_example.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.model.Country;

import java.util.List;

/**
 * Created by redlongcity on 30.07.2017.
 */

public class CountryAdapter extends ArrayAdapter<Country> {
    private List<Country> items;

    public CountryAdapter(Context context, List<Country> items) {
        super(context, R.layout.country_model, items);
        this.items=items;
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.country_model,null);
        }
            Log.i("Adapter", "Getting Adapter");
            Country country = items.get(position);
        if (country != null) {
            ((TextView) view.findViewById(R.id.cm_country_field)).setText(country.getTitle());
        }
        return view;
    }
}
