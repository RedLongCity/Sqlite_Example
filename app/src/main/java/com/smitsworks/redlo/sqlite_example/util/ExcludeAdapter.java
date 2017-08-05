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
import com.smitsworks.redlo.sqlite_example.model.DataPoint;

import java.util.List;

/**
 * Created by redlongcity on 05.08.2017.
 */

public class ExcludeAdapter extends ArrayAdapter<DataPoint> {
    private List<? extends DataPoint> items;

    public ExcludeAdapter(Context context, List<? extends DataPoint> items) {
        super(context, R.layout.country_model, (List<DataPoint>) items);
        this.items=items;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.country_model,null);
        }
        Log.i("Adapter", "Getting Exclude Adapter");
        DataPoint dataPoint = items.get(position);
        if (dataPoint != null) {
            ((TextView) view.findViewById(R.id.cm_country_field)).setText(dataPoint.getTitle());
        }
        return view;
    }
}
