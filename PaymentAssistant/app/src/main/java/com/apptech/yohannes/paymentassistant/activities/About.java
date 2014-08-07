package com.apptech.yohannes.paymentassistant.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.apptech.yohannes.paymentassistant.R;

public class About extends Activity {
    private Button btnContact;
    private ImageButton btnAbout;
    private TextView txtMessage;

    private int _click_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        btnAbout = (ImageButton)findViewById(R.id.imgBtnAbout);
        btnContact = (Button)findViewById(R.id.btnContact);
        txtMessage = (TextView)findViewById(R.id.txtMessage);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _click_count++;

                if(_click_count == 5)
                {
                    txtMessage.setText("Yohannes Tamru\r\n\r\nyohannist@gmail.com");
                }
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON);
            }
        });
    }
}
