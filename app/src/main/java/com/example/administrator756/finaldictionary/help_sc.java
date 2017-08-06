package com.example.administrator756.finaldictionary;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Administrator756 on 12/6/2016.
 */

public class help_sc extends AppCompatActivity {
    TextView text_head, text_cont ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        text_head = (TextView) findViewById(R.id.help_head);
        text_head.setText(" HELP ");
        text_cont = (TextView) findViewById(R.id.help_cont);
        String s = "\n నిఘంటువు \n\n Version : 1.0 \n Android Versions : 5.0 and above"
                + " \n\nKeypads : \nInstall Telugu & Hindi Keypads \n Ex. Google Indic Keyboard"
                + " \n\nTranslation : \nFor Translation select the language button on top right corner"
                + " \nChoose the appropriate Keypad"
                + " \nType the word to in search box \nClick the Search icon"
                + " \n\nBookmark : \nAfter typing/searching the word click the star icon to add it to the favourites"
                + " \n\nFavourites : View all the words bookmarked "
                + " \n\nRecent : View all the words translated recently"
                + " \n\nLong Press the words stored in Recent and Favourites for additional options "
                + "\n\n\n\n\n\n\n\n";
        text_cont.setText(s);
    }
}
