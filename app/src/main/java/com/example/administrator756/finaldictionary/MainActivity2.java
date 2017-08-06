package com.example.administrator756.finaldictionary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.administrator756.finaldictionary.R.layout.activity_main2;

public class MainActivity2 extends AppCompatActivity {

    private SQLiteDatabase data ;
    AutoCompleteTextView in ;
    TextView out ;
    ImageButton ok, fav_b, rec_b, help_b, lan_b ;
    int flag_fav = 0 ;
    public int l = 0 ;
    private String lang, lang2, lang3 ;

    public static String[] recent = new String[20];
    public static int count_rec = 0 ;

    public void toast(String inp){
        Toast toast = Toast.makeText(MainActivity2.this, inp, Toast.LENGTH_SHORT) ;
        toast.setGravity(Gravity.CENTER, 0, 100);
        toast.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        setContentView(activity_main2);
        final  DataBaseAccess databaseAccess = DataBaseAccess.getInstance(this) ;
        databaseAccess.open();

        Intent intent = getIntent();
        String pos = intent.getStringExtra("pos") ;
        String val=" " ;
        String pos1 = "one" ;
        String pos2 = "two" ;
        try{
            if(pos.equals(pos1))
                val = intent.getStringExtra("fav_val");
            else if(pos.equals(pos2))
                val = intent.getStringExtra("rec_val");
            else
                val = intent.getStringExtra("home");

        }catch (Exception e){

        }
        lan_b = (ImageButton) findViewById(R.id.lang) ;
        ok = (ImageButton) findViewById(R.id.okay) ;
        in = (AutoCompleteTextView) findViewById(R.id.input) ;

        in.setText(val);

        out = (TextView) findViewById(R.id.output) ;
        fav_b = (ImageButton) findViewById(R.id.fav_but) ;
        rec_b = (ImageButton) findViewById(R.id.rec_but) ;
        autocomplete();
        if(l == 0){
            lan_b.setBackgroundResource(R.drawable.alpha_e);
            toast("Please Change Keyboard to English") ;
            //  in_val = databaseAccess.getList(l);

        }
        else if(l == 1){
            lan_b.setBackgroundResource(R.drawable.alpha_t);
            toast("Please Change Keyboard to Telugu") ;
            // in_val = databaseAccess.getList(l);

        }
        else if(l == 2){
            lan_b.setBackgroundResource(R.drawable.alpha_h) ;
            toast("Please Change Keyboard to Hindi") ;
            //in_val = databaseAccess.getList(l);

        }


        ok.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(in != null)
                    imm.hideSoftInputFromWindow(in.getWindowToken(), 0);
                String inp_s = in.getText().toString().trim() ;
                String out_s[] = new String[2] ;
                int flag = -1 ;
                if(!(inp_s.isEmpty())) {
                    if(l == 0){
                        try{
                            out_s = databaseAccess.getQuery(inp_s, l);
                            out.setText("\n తెలుగు : " + out_s[0] + "\n\n हिंदी : " + out_s[1]);

                            flag = 0 ;
                        }catch(Exception e){
                            out.setText("");
                            in.setText("");
                            toast(" Word Not Found ");
                            }
                    }
                    else if(l == 1){
                        try{
                            out_s = databaseAccess.getQuery(inp_s, l);
                            out.setText("\n English : " + out_s[0] + "\n\n हिंदी : " + out_s[1]);
                            flag = 1 ;
                        }catch(Exception e){
                            out.setText("");
                            in.setText("");
                            toast(" Word Not Found ");
                        }
                    }
                    else if(l == 2){
                        try{
                            out_s = databaseAccess.getQuery(inp_s, l);
                            out.setText("\n English : " + out_s[0] + "\n\n తెలుగు : " + out_s[1]);
                            flag = 2 ;

                        }catch(Exception e){
                            out.setText("");
                            in.setText("");
                            toast(" Word Not Found ");
                        }
                    }
                    if(flag != -1)
                    try{
                        databaseAccess.insert_recent(inp_s);
                        boolean s = databaseAccess.get_fav(inp_s, flag);
                        if(s == true){
                            fav_b.setActivated(true);
                        }
                        else
                         fav_b.setActivated(false);
                    }catch(Exception e){

                    }

                }else{
                    out.setText("");
                    toast("Enter word to search");
                }

            }
        });

        fav_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inp_s = in.getText().toString().trim() ;
                boolean flag = false ;
                String out_s[] = new String[2] ;
                int i = 0;
                if(inp_s.isEmpty()){
                    toast("Enter Word to search");
                    out.setText("");
                }
                else{
                    if(l == 0)
                        flag = databaseAccess.get_fav(inp_s, 0) ;
                    else if(l == 1)
                        flag = databaseAccess.get_fav(inp_s, 1) ;
                    else if(l ==2 )
                        flag = databaseAccess.get_fav(inp_s, 2) ;

                    if(flag == false){
                        try{
                            if(l == 0){
                                try{
                                    out_s = databaseAccess.getQuery(inp_s, l);
                                    i = 1 ;
                                    out.setText("\n తెలుగు : " + out_s[0] + "\n\n हिंदी : " + out_s[1]);
                                }catch(Exception e){
                                    out.setText("");
                                    in.setText("");
                                    toast(" Word Not Found ");                        }
                            }
                            else if(l == 1){
                                try{
                                    out_s = databaseAccess.getQuery(inp_s, l);
                                    i = 1 ;
                                    out.setText("\n English : " + out_s[0] + "\n\n हिंदी : " + out_s[1]);
                                }catch(Exception e){
                                    out.setText("");
                                    in.setText("");
                                    toast(" Word Not Found ");
                                }
                            }
                            else if(l == 2){
                                try{
                                    out_s = databaseAccess.getQuery(inp_s, l);
                                    i = 1 ;
                                    out.setText("\n English : " + out_s[0] + "\n\n తెలుగు : " + out_s[1]);
                                }catch(Exception e){
                                    out.setText("");
                                    in.setText("");
                                    toast(" Word Not Found ");
                                }
                            }
                            if(i == 1){
                                databaseAccess.insert_fav(inp_s);
                                toast(" Added to Favourites ");
                                fav_b.setActivated(true);
                            }
                        }catch(Exception e){

                        }

                    }
                    else{
                        if(l == 0 || l == 2){
                            databaseAccess.del_fav(" " +inp_s);
                        }
                        else if(l == 1){
                            databaseAccess.del_fav(inp_s);
                        }
                        toast(" Removed from Favourites ") ;
                        fav_b.setActivated(false);
                    }
                }

            }
        });

        fav_b.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                Intent intent = new Intent(MainActivity2.this, favourites_sc.class) ;
                startActivity(intent);
                return true;
            }
        });
        rec_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity2.this, recent_sc.class) ;
                startActivity(intent);
            }
        } );
        help_b = (ImageButton) findViewById(R.id.help_but) ;
        help_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity2.this, help_sc.class) ;
                startActivity(intent);
            }
        } );

        lan_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String lan, country ;
                change_lan() ;
            }
        });
    }

    public void change_lan(){
        if(l == 0){
            l = 1 ;
            lan_b.setBackgroundResource(R.drawable.alpha_t);
            autocomplete();
            toast("Please Change Keyboard to Telugu") ;
        }
        else if(l == 1) {
            l = 2;
            lan_b.setBackgroundResource(R.drawable.alpha_h);
            autocomplete();
            toast("Please Change Keyboard to Hindi") ;

        }
        else {
            l = 0 ;
            lan_b.setBackgroundResource(R.drawable.alpha_e);
            autocomplete();
            toast("Please Change Keyboard to English") ;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId() ;

        if(id == R.id.item_1 ){
            Intent intent = new Intent(this, favourites_sc.class) ;
            startActivity(intent);
            return true ;
        }
        else if(id == R.id.item_2 ){
            Intent intent = new Intent(this, recent_sc.class) ;
            startActivity(intent);
            return true ;
        }
        else if(id == R.id.item_3 ){
            Intent intent = new Intent(this, help_sc.class) ;
            startActivity(intent);
            return true ;
        }
        else if(id == R.id.item_4 ){
            Intent intent = new Intent(this, about_sc.class) ;
            startActivity(intent);
            return true ;
        }
        else if(id == R.id.item_5){
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

    public void autocomplete(){
        final  DataBaseAccess databaseAccess = DataBaseAccess.getInstance(this) ;
        databaseAccess.open();

        String in_val[] = databaseAccess.getList(l);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview, in_val);
        in.setThreshold(1);
        in.setAdapter(adapter);

    }


}
