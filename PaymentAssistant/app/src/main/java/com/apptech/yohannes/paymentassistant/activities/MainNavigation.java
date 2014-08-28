package com.apptech.yohannes.paymentassistant.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.apptech.yohannes.paymentassistant.R;

/**
 * Created by Yohannes on 8/28/2014.
 */
public class MainNavigation extends Activity {
    private FrameLayout mainContentView;
    private ListView drawerMenu;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        setContentView(R.layout.activity_main_navigation);

        mainContentView = (FrameLayout) findViewById(R.id.mainContent);
        drawerMenu = (ListView)findViewById(R.id.navigationDrawerList);

    }
}
