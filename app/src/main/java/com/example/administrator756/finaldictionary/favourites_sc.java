package com.example.administrator756.finaldictionary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator756 on 12/6/2016.
 */

public class favourites_sc extends AppCompatActivity {

    List<String> list_items = new ArrayList<String>();
    SQLiteDatabase data ;
    DataBaseAccess databaseAccess = DataBaseAccess.getInstance(this) ;
    ImageButton clear_b ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final DBHelper db = new DBHelper(this);
        clear_b = (ImageButton) findViewById(R.id.clear_but) ;
        clear_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClearKey();
            }
        });


        try{
            data = openOrCreateDatabase("SampleDict.sqlite", Context.MODE_PRIVATE, null) ;
            Cursor c = data.rawQuery(" SELECT NAME FROM FAVOURITE ", null) ;
            c.moveToLast() ;
            for(int i = c.getCount()-1 ; i >= 0  ; i--){
                try{

                    list_items.add(c.getString(0)) ;
                    c.moveToPrevious();
                }catch(Exception n){
                    throw new Error(" e") ;
                }

            }


        }catch(Exception e){

        }


        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_settings, list_items) ;
       final ListView list = (ListView)findViewById(R.id.fav_list);
        list.setAdapter(adapter);

        View v = new View(this);
        v.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, 1));
        list.addFooterView(v);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = list.getItemAtPosition(i).toString() ;
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("fav_val", s);
                intent.putExtra("pos", "one") ;
                startActivity(intent) ;
            }
        });

        registerForContextMenu(list);

}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuinfo){
        super.onCreateContextMenu(menu, v , menuinfo);

        if(v.getId() == R.id.fav_list) {
            MenuInflater inflator = getMenuInflater();
            inflator.inflate(R.menu.fav_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo() ;
        int pos = info.position ;
        String s = list_items.get(pos);
        databaseAccess.open();

        switch(item.getItemId()){
            case R.id.remove_from_favourites :
                databaseAccess.del_fav(s);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                Toast.makeText(favourites_sc.this,"Removed from Favourites", Toast.LENGTH_SHORT).show();
                return true ;
            default: return super.onContextItemSelected(item) ;
                }

    }
    protected void onClearKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to clear all words?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        databaseAccess.fav_trunc() ;
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        Toast toast = Toast.makeText(favourites_sc.this, "Cleared All", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 100);
                        toast.show() ;
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }
}
