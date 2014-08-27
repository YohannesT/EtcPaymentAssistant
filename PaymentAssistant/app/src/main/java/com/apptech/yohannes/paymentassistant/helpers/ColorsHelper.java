package com.apptech.yohannes.paymentassistant.helpers;


import android.graphics.Color;

import java.util.Random;

/**
 * Created by Yohannes on 7/30/2014.
 */
public class ColorsHelper {

    private static String colors = "#336699,\n" +
            "#FF6600,\n" +
            "#336699,\n" +
            "#666666,\n" +
            "#663399,\n" +
            "#8C001A,\n" +
            "#028482,\n" +
            "#CC0000,\n" +
            "#3399FF,\n" +
            "#287AA9,\n" +
            "#006699,\n" +
            "#0099CC,\n" +
            "#5BC236,\n" +
            "#990033,\n" +
            "#FF9900,\n" +
            "#FF0080,\n" +
            "#21B6A8,\n" +
            "#FF8000,\n" +
            "#266A2E,\n" +
            "#BD2031,\n" +
            "#3366CC,\n" +
            "#6699CC,\n" +
            "#6699FF,\n" +
            "#D08AAF,\n" +
            "#7A3E48,\n" +
            "#F20056,\n" +
            "#92CD00,\n" +
            "#006666,\n" +
            "#4086AA";
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

    public static int GetRandomStaticColor()
    {
        String [] clr = colors.split(",");
        return Color.parseColor(clr[random.nextInt(clr.length -1)].trim());
    }
}
