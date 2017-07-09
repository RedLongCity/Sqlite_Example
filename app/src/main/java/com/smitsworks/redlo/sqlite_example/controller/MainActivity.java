package com.smitsworks.redlo.sqlite_example.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.smitsworks.redlo.sqlite_example.R;
import com.smitsworks.redlo.sqlite_example.util.DataBaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText cityText = (EditText) findViewById(R.id.txCity);
        EditText countryText = (EditText) findViewById(R.id.txCountry);
        TextView textArea = (TextView) findViewById(R.id.txArea);
        Button addButton = (Button) findViewById(R.id.btnAdd);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       if(dataBaseHelper !=null) {
           OpenHelperManager.releaseHelper();
           dataBaseHelper=null;
       }
    }
    private DataBaseHelper getHelper(){
        if(dataBaseHelper==null){
            dataBaseHelper = new DataBaseHelper(getApplicationContext());
        }
        return dataBaseHelper;
    }

    public void addStufs(View v){
        doSampleDataBaseStuff();
    }

    private void doSampleDataBaseStuff(EditText et1, EditText et2, TextView tv){
        if(et1.getText().length()==0||et1.getText().length()==0){
            Toast.makeText(this,"Fill both fiels",Toast.LENGTH_SHORT);
            return;
        }

    }
}
