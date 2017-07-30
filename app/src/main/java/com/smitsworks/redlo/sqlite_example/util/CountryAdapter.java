package com.smitsworks.redlo.sqlite_example.util;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public CountryAdapter(@NonNull Context context, @LayoutRes int resource, List<Country> items) {
        super(context, resource);
        this.items=items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            Context context = getContext();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.country_model,null);
        }
            Log.i("Adapter", "Getting Adapter");
            Country country = items.get(position);
        if (country != null) {
            ((TextView) view.findViewById(R.id.countryField)).setText(country.getTitle());
        }
        return view;
    }
}
