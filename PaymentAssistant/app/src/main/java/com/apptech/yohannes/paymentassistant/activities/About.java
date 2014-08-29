package com.apptech.yohannes.paymentassistant.activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apptech.yohannes.paymentassistant.R;

public class About extends Fragment {
    private Button btnContact;
    private ImageButton btnAbout;
    private TextView txtMessage;

    private int _click_count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        btnAbout = (ImageButton)view.findViewById(R.id.imgBtnAbout);
        btnContact = (Button)view.findViewById(R.id.btnContact);
        txtMessage = (TextView)view.findViewById(R.id.txtMessage);

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

        return view;
    }
}
