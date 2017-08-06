package com.example.administrator756.finaldictionary;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator756 on 12/7/2016.
 */

public class settings_sc extends AppCompatActivity {
    List<String> list_items = new ArrayList<String>();
    SQLiteDatabase data;
    DataBaseAccess databaseAccess = DataBaseAccess.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_settings);
        list_items.add("Default Language");
        list_items.add("Clear Favourites") ;
        list_items.add("Clear Recent");
        list_items.add("Clear all") ;

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_settings, list_items);
        final ListView list = (ListView) findViewById(R.id.settings_list);
        list.setAdapter(adapter);

        View v = new View(this);
       v.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT ));
   //     v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        list.addFooterView(v);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        registerForContextMenu(list);
    }

}