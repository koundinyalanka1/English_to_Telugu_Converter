package com.example.administrator756.finaldictionary;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Administrator756 on 12/6/2016.
 */

public class about_sc extends AppCompatActivity {
    TextView text_head, text_cont ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_about);

        text_head = (TextView)findViewById(R.id.about_text);
        text_cont = (TextView) findViewById(R.id.about_text2);
        String s = " ABOUT " ;
        String sin = " నిఘంటువు \n\n Version 1.0 \n\nCompatible to Android Versions 5.0 and above \n"
                + "\n\n Compiled By : \n \t Sri Peddi Sambasiva Rao"
                + " \n\n Authors : \n \t M. Praveena \n \t B. Solomon Chrysostham"
                + "\n\nApplication developed under the guidance of CSE Department, VVIT, Nambur"
                + "\n\n For any queries or suggestions \ncontact : mailtoserve87@gmail.com"
                + "\n\n\n";
        text_head.setText(s);
        text_cont.setText(sin);
    }
}
