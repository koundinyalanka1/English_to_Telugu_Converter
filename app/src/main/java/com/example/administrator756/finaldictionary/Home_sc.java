package com.example.administrator756.finaldictionary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.example.administrator756.finaldictionary.R.layout.activity_home;

/**
 * Created by Administrator756 on 1/22/2017.
 */
public class Home_sc extends AppCompatActivity{
   Button gen, math ;

   @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      setContentView(activity_home);

       gen = (Button) findViewById(R.id.main_1);
     gen.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view) {
            Intent intent = new Intent(Home_sc.this, MainActivity.class);
             startActivity(intent);
         }

      });
       math = (Button) findViewById(R.id.main_2);
        math.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_sc.this, MainActivity2.class);
                startActivity(intent);
            }

        });
   }@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homemenu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId() ;

         if(id == R.id.item_1 ){
            Intent intent = new Intent(this, help_sc.class) ;
            startActivity(intent);
            return true ;
        }
        else if(id == R.id.item_2 ){
            Intent intent = new Intent(this, about_sc.class) ;
            startActivity(intent);
            return true ;
        }
         else if(id == R.id.item_3){
             AlertDialog alertbox = new AlertDialog.Builder(this)
                     .setMessage("Do you want to exit application?")
                     .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                         // do something when the button is clicked
                         public void onClick(DialogInterface arg0, int arg1) {
                             finishAffinity();
                             finish();
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

        return super.onOptionsItemSelected(item) ;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                        finish();
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
