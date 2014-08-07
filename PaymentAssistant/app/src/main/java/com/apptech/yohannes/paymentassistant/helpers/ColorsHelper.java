package com.apptech.yohannes.paymentassistant.helpers;


import android.graphics.Color;

import java.util.Random;

/**
 * Created by Yohannes on 7/30/2014.
 */
public class ColorsHelper {

    private static String colors = "#d0822f,#45ba8f,#910324,#368cc9,#5c5cff,#cacef1,#b7b5ee,#58a799,#e88017,#b7db24,#62c43b,#a33dc2,#ad5289,#8d6996,#63699c,#ad5252,#2036df,#986795,#553606,#5f0130,#5588aa,#d02f72,#7c8083,#f6094c";
    private static Random random = new Random();

    public static int GetRandomColor()
    {
        int diff = random.nextInt(200);
        int red = random.nextInt(125);
        int green = (red + diff) < 255 ? red + diff : (red + diff) - 255;
        int blue = (green + diff) < 255 ? green + diff : (green + diff) - 255;

        float[] hsvColor = new float[3];
        Color.RGBToHSV(red, green, blue, hsvColor);

        if( (green * 2 >= red + blue)  || (red * 2 >= green + blue))
            GetRandomColor();

        if(hsvColor[2] > 200) //avoid overly bright colors
            hsvColor[2] = random.nextInt(200);

        return  Color.HSVToColor(170, hsvColor);
    }

    public static int GetStaticRandomColor()
    {
        String [] clr = colors.split(",");
        return Color.parseColor(clr[random.nextInt(clr.length -1)]);
    }
}
