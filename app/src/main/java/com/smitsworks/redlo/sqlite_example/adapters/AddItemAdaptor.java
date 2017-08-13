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
import android.widget.Button;
import android.widget.TextView;

import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.model.DataPoint;

import java.util.List;

/**
 * Created by redlo on 12.08.2017.
 */

public class AddItemAdaptor extends ArrayAdapter<DataPoint> {

    private List<? extends DataPoint> items;
    private View.OnClickListener onClickListener;

    public AddItemAdaptor(@NonNull Context context, View.OnClickListener listener,
                          List<? extends DataPoint> items) {
        super(context, R.layout.add_item,(List<DataPoint>) items);
        this.onClickListener = listener;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.add_item,null);
        }
        Log.i("Adapter", "Getting Add_Item Adapter");
        DataPoint dataPoint = items.get(position);
        if (dataPoint == null) {
            ((TextView) view.findViewById(R.id.add_item_textView)).setText(dataPoint.getTitle());
            Button button1 = (Button)view.findViewById(R.id.add_item_button_change);
            button1.setTag(1,dataPoint);
            button1.setTag(2,position);
            button1.setOnClickListener(onClickListener);
            Button button2 = (Button) view.findViewById(R.id.add_item_button_delete);
            button2.setTag(1,dataPoint);
            button2.setTag(2,position);
            button2.setOnClickListener(onClickListener);
        }
        return super.getView(position, convertView, parent);
    }
}
