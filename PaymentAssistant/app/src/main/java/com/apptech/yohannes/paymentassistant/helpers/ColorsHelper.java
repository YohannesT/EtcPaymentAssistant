package com.apptech.yohannes.paymentassistant.helpers;


import android.content.Context;
import android.graphics.Color;

import com.apptech.yohannes.paymentassistant.R;

import java.util.Random;

/**
 * Created by Yohannes on 7/30/2014.
 */
public class ColorsHelper {
    private static Random random = new Random();

    public static int GetRandomColor()
    {
        int diff = random.nextInt(200);
        int red = random.nextInt(200);
        int green = (red + diff) < 255 ? red + diff : (red + diff) - 255;
        int blue = (green + diff) < 255 ? green + diff : (green + diff) - 255;

        float[] hsvColor = new float[3];
        Color.RGBToHSV(red, green, blue, hsvColor);

        //if( (green * 2 >= red + blue)  || (blue * 2 >= green + red))
          //  GetRandomColor();

        if(hsvColor[2] > 20) //avoid bright colors
            hsvColor[2] = random.nextInt(5);

        return  Color.HSVToColor(150, hsvColor);
    }

    public static int GetRandomStaticColor(Context context)
    {
        context.getResources().getStringArray(R.array.colorArray);
        String [] clr = context.getResources().getStringArray(R.array.colorArray);
        return Color.parseColor(clr[random.nextInt(clr.length -1)].trim());
    }
}
