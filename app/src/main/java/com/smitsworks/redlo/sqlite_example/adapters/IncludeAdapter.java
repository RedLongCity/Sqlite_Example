package com.smitsworks.redlo.sqlite_example.adapters;

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
import com.smitsworks.redlo.sqlite_example.model.DataPoint;

import java.util.List;


/**
 * Created by redlo on 05.08.2017.
 */

public class IncludeAdapter extends ArrayAdapter<DataPoint> {
    private List<? extends DataPoint> items;

    public IncludeAdapter(@NonNull Context context, List<? extends DataPoint> items) {
        super(context, R.layout.include_model,(List<DataPoint>) items);
        this.items=items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.include_model,null);
        }
        Log.i("Adapter", "Getting Include Adapter");
        DataPoint dataPoint = items.get(position);
        if (dataPoint != null) {
            ((TextView) view.findViewById(R.id.in_model_text_view)).setText(dataPoint.getTitle());
        }
        return view;
    }
}
